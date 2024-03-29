package com.shatteredpixel.shatteredpixeldungeon.items.treasure;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfBlastWave;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class VibraniumShield extends MissileWeapon {
    {
        image = ItemSpriteSheet.VIBRANIUM_SHIELD;
        hitSound = Assets.Sounds.HIT_CRUSH;
        hitSoundPitch = 1f;
        sticky = false;
        unique = true;
        treasure=true;
    }
    @Override
    public int proc(Char attacker, Char defender, int damage ) {

        //trace a ballistica to our target (which will also extend past them
        Ballistica trajectory = new Ballistica(attacker.pos, defender.pos, Ballistica.STOP_TARGET);
        //trim it to just be the part that goes past them
        trajectory = new Ballistica(trajectory.collisionPos, trajectory.path.get(trajectory.path.size()-1), Ballistica.PROJECTILE);
        //knock them back along that ballistica
        WandOfBlastWave.throwChar(defender,
                trajectory,
                2,
                true,
                true,
                getClass());

        return damage;
    }
}
