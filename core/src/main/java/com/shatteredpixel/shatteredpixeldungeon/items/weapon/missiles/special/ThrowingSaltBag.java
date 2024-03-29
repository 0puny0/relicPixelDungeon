package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.special;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Wraith;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfBlastWave;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.TenguDartTrap;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;

import java.util.ArrayList;

public class ThrowingSaltBag extends MissileWeapon {
    {
        image = ItemSpriteSheet.THROWING_SALT_BAG;
        hitSound = Assets.Sounds.HIT;
        hitSoundPitch = 1.1f;
        baseUses = 15;
        tier = 2;
        sticky = false;
    }
    @Override
    public void hitSound(float pitch) {
        //no hitsound as it never hits enemies directly
    }

    @Override
    protected void onThrow(int cell) {
        if (Dungeon.level.pit[cell]||durability<durabilityPerUse()){
            super.onThrow(cell);
            return;
        }

        rangedHit( null, cell );
        Dungeon.level.pressCell(cell);

        ArrayList<Char> targets = new ArrayList<>();
        if (Actor.findChar(cell) != null) targets.add(Actor.findChar(cell));

        for (int i : PathFinder.NEIGHBOURS8){
            if (Actor.findChar(cell + i) != null) targets.add(Actor.findChar(cell + i));
        }

        for (Char target : targets){
           if (target instanceof Wraith) target.die(this);
        }

        Sample.INSTANCE.play( Assets.Sounds.DROP );
    }

}
