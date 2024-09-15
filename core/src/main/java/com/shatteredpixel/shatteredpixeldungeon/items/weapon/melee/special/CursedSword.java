package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.special;
import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Bundle;

public class CursedSword extends MeleeWeapon {
    {
        image = ItemSpriteSheet.CURSED_SWORD;
        hitSound = Assets.Sounds.HIT_SLASH;
        hitSoundPitch = 1f;
        DMG = Attribute.lower;
    }

    @Override
    public Weapon enchant( Enchantment ench) {
        if(!hasCurseEnchant()&&ench!=null&&ench.curse()){
            modeSwitch();
        }
        if (hasCurseEnchant()&&(ench == null || !ench.curse())){
            modeSwitch();
        }
        return super.enchant( ench);
    }

    @Override
    public boolean removeCurse(boolean extraEffect) {
        boolean hCE=hasCurseEnchant();
        boolean doWell =super.removeCurse(extraEffect);
        if(extraEffect&&hCE){
            modeSwitch();
        }
        return doWell;
    }

    public void modeSwitch() {
        if(hasCurseEnchant()){
            image = ItemSpriteSheet.CURSED_SWORD_;
            hitSound = Assets.Sounds.HIT_SLASH;
            DMG = Attribute.highest;
            ACC=Attribute.lower;
        }else{
            image = ItemSpriteSheet.CURSED_SWORD;
            hitSound = Assets.Sounds.HIT_SLASH;
            DMG = Attribute.lower;
            ACC=Attribute.common;
        }
    }
    public String desc() {
            if (hasCurseEnchant()){
                return Messages.get(this,"typical_desc");
            }else {
                return Messages.get(this,"desc");
            }
    }
    @Override
    public void restoreFromBundle( Bundle bundle ) {
        super.restoreFromBundle( bundle );
        if(hasCurseEnchant()){
            modeSwitch();
        }
    }
}
