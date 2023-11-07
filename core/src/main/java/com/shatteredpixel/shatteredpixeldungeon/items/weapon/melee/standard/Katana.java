package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.standard;
import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Aim;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Weakness;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfForce;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class Katana extends MeleeWeapon {
    {
        image = ItemSpriteSheet.KATANA;
        hitSound = Assets.Sounds.HIT_SLASH;
        hitSoundPitch=0.95f;
        ACC=Attribute.lower;
    }

    @Override
    public int damageRoll(Char owner) {
        if (owner instanceof Hero&&owner.buff(Aim.class)!=null) {
            Hero hero = (Hero)owner;
            int diff = max() - min();
            int damage = Random.NormalIntRange(min()+ Math.round(diff*0.4f),
                    max());
            int exStr = hero.STR() - STRReq();
            if (exStr > 0) {
                damage +=   (int)(exStr * RingOfForce.extraStrengthBonus(hero ));
            }
            return damage;
        }
        return super.damageRoll(owner);
    }


}
