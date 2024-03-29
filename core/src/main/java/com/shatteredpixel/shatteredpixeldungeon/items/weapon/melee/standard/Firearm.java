package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.standard;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;

public class Firearm extends MeleeWeapon {
    {
        image = ItemSpriteSheet.FIREARM;
        hitSound = Assets.Sounds.HIT_CRUSH;
        hitSoundPitch=1f;
        DMG=Attribute.lower;
        ACC=Attribute.lower;
        hasSkill=true;
        defaultAction=AC_WEAPONSKILL;
    }
    @Override
    public void execute(Hero hero, String action) {

        super.execute(hero, action);

        if (!isEquipped(hero)){
            GLog.i( Messages.get(MeleeWeapon.class, "need_to_equip") );
            return;
        }
        if (action.equals(AC_WEAPONSKILL)) {

        }
    }

    public class Bullet extends MissileWeapon {

    }

}
