package com.shatteredpixel.shatteredpixeldungeon.items.treasure;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Freezing;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfBlastWave;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.TenguDartTrap;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;

import java.util.ArrayList;
public class FrostCube extends MissileWeapon {

    {
        image = ItemSpriteSheet.FROST_CUBE;
        tier = 5;
        baseUses = 5;
        sticky = false;
        unique = true;
        treasure=true;
    }

    @Override
    public void hitSound(float pitch) {

    }

    @Override
    protected void onThrow(int cell) {
        if (Dungeon.hero.STR()<STRReq()||Dungeon.level.pit[cell]||durability<durabilityPerUse()){
            super.onThrow(cell);
            return;
        }

        rangedHit( null, cell );
        Dungeon.level.pressCell(cell);

        for (int offset : PathFinder.NEIGHBOURS9){
            if (!Dungeon.level.solid[cell+offset]) {
                GameScene.add(Blob.seed(cell + offset, 10, Freezing.class));
            }
        }
        Sample.INSTANCE.play( Assets.Sounds.DROP );
    }
}
