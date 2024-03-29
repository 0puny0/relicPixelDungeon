package com.shatteredpixel.shatteredpixeldungeon.ui.changelist;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.SandalsOfNature;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfArcana;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfWealth;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfAntiMagic;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.TelekineticGrab;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfTransfusion;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.ChangesScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.sprites.RatSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ShopkeeperSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.SpectralNecromancerSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.StatueSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.ui.TalentIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.watabou.noosa.Image;

import java.util.ArrayList;

public class v0_X_Changes {


    public static void addAllChanges( ArrayList<ChangeInfo> changeInfos ){
        add_v0_4_Changes(changeInfos);
    }

    public static void add_v0_4_Changes( ArrayList<ChangeInfo> changeInfos ) {
        ChangeInfo changes = new ChangeInfo("遗迹地牢", true, "");
        changes.hardlight(Window.TITLE_COLOR);
        changeInfos.add(changes);

        changes.addButton(new ChangeButton(new ItemSprite(ItemSpriteSheet.RICH_CHEST), "开发者留言",
                "_-_ 2024年3月发布\n" +
                        "\n" +
                        "遗迹地牢基于破碎1.4.3版本，我希望在保留破碎像素地牢的基本体验的同时为游戏加入更多玩法和内容！！"));

        changes = new ChangeInfo("系统性改动", false, null);
        changes.hardlight(Window.TITLE_COLOR);
        changeInfos.add(changes);

        changes.addButton(new ChangeButton(new ItemSprite(ItemSpriteSheet.WEAPON_HOLDER), "近战武器",
                "_-_ 近战武器被分为三种类型：初始武器，制式武器，特殊武器。\n_- _初始武器由角色自带，不会在地牢中自然生成。\n_- _制式武器会在地牢中生成，并且根据楼层调整等阶。\n_- _特殊武器只会在固定等阶生成，并且稀有度更高"+
                        "\n\n" + "_-_ 近战武器下方会显示它的武器属性，武器属性决定了武器最基本的特征。此外，部分武器可能具有特殊能力"+
                        "\n\n" + "_-_ 更多的武器种类。"
        ));
        changes.addButton(new ChangeButton(new ItemSprite(ItemSpriteSheet.MISSILE_HOLDER), "投掷武器",
                "_-_ 投掷武器被分为四种类型：初始武器，制式武器，特殊武器，飞镖。\n_-_ 初始武器由角色自带，不会在地牢中自然生成。\n_-_ 制式武器会在地牢中生成，并且根据楼层调整等阶。\n_-_ 特殊武器只会在固定等阶生成，并且稀有度更高。\n_-_ 飞镖只在且必定在商店刷新，并且不会损耗耐久。"+
                        "\n\n" + "_-_ 投掷武器不会轻易损坏，但耐久损耗后无法正常使用。（后续版本部分投掷武器会增加无法修复属性） "+
                        "\n\n" + "_-_ 更多的投掷武器，投掷武器加入道具图鉴。"
        ));
        changes.addButton(new ChangeButton(new ItemSprite(ItemSpriteSheet.FOOD_HOLDER), "进食",
                "_-_ 饥饿状态为：极度饥饿、饥饿、常态、饱腹、过饱。\n_-_ 其中饥饿（黄饿）不再自然恢复生命，饱腹会提供50%的自然恢复加成，过饱在提高自然恢复加成的同时会降低角色命中和闪避，并且无法再次进食"+
                        "\n\n" + "_-_ 自然恢复每10回合获得英雄生命1%的生命恢复，且不会低于1点" +
                        "\n\n" + "_-_ 食物现在提供的饱食和食用消耗的时间各不相同，但往往能根据食物的种类和食物的特点判断。"+
                        "\n\n" + "_-_ 角色的进食天赋不再加快进食速度，并且提供的效果会根据恢复的饱食动态调整。"+
                        "\n\n" + "_-_ 角色初始携带3干粮。"
                        ));
        changes = new ChangeInfo("重大改动", false, null);
        changes.hardlight(Window.TITLE_COLOR);
        changeInfos.add(changes);
        changes.addButton(new ChangeButton(Icons.get(Icons.TALENT), "角色天赋",
                "_-_ 角色初始携带着符合其特性的初始武器，拥有额外的初始被动，并且携带着一个独特道具。" +
                        "\n\n" +"_-_ 每个角色的天赋或多或少都发生了变化，具体请从游戏中体验" +
                        " "));
        changes.addButton(new ChangeButton(new ItemSprite(ItemSpriteSheet.MASK), "转职",
                "_-_ 角斗士重做：角斗士会在成功进行攻击时积累连击数，用以释放角斗士的特殊战技和终结技。" +
                        "\n\n" +"_-_ 狂战士重做：狂战士攻击能造成更多伤害，并且生命越低伤害越高。但每次攻击时都会损失一些生命。"+
                        "\n\n" +"_-_ 狙击手不再忽视护甲，但能自由切换追击方式。"));
        changes.addButton(new ChangeButton(new Image(new RatSprite()), "敌人",
                "_-_ 敌人的闪避大幅度减少。"+
                        "\n\n" +"_-_ 敌人的护甲大幅度提升。"+
                        "\n\n" + "_-_ 咕咕的蓄力攻击变为范围攻击"+
                        "\n\n" +"_-_ 豺狼诡术师的移速变为0.9。"
        ));

        changes = new ChangeInfo("内容调整", false, null);
        changes.hardlight(Window.TITLE_COLOR);
        changeInfos.add(changes);

        changes.addButton(new ChangeButton(new ItemSprite(ItemSpriteSheet.CEREMONIAL_DAGGER), "神器",
                "_-_ 干枯玫瑰加强：幽妹在移动时会潜行，不会被敌人发现。但靠近敌人后会暴露。" +
                        "\n\n" + "_-_ 新增仪式匕首：通过献祭鲜血来获取召唤怨灵助战的能力"+
                        "\n\n" + "_-_ 新增念力手套：减少投掷武器损耗，并且可以抓取地上的物品和敌人身上卡住的投掷武器 ",""
        ));
        changes.addButton(new ChangeButton(new ItemSprite(ItemSpriteSheet.RING_RUBY), "戒指",
                "_-_ 新增统率之戒：全体友军获得额外伤害和伤害减免。"+
                        "\n\n" +"_-_ 新增武力之戒：获得额外的物理伤害加成。"+
                        "\n\n" +"_-_ 移除精准之戒、韧性之戒、旧武力之戒。"+
                        "\n\n" +"_-_ 根骨之戒直接提供的生命加成提高。"+
                        "\n\n" +"_-_ 神射之戒不再提供额外耐久。"
        ));
        changes.addButton(new ChangeButton(Icons.get(Icons.CHALLENGE_ON), "挑战",
                        "_-_ 新增挑战：精英强敌、伤痛难愈"+
                        "\n\n" +"_-_ 移除挑战：信念护体、恐药异症"+
                        "\n\n" +"_-_ 原精英强敌挑战更名为力量授予"+
                        "\n\n" +"_-_ 缩餐节食不会减少食物提供的饱食度，而是使英雄饿的更快"
                        ));
        changes.addButton(new ChangeButton(new Image(new ShopkeeperSprite()), "商店",
                        "_-_ 商店中会售卖带有附魔和等级的护甲和武器。"+
                                "\n\n" + "_-_ 商店会刷出新的物品"+
                                "\n\n" + "_-_ 道具出售价格大大提高"
        ));
        changes.addButton(new ChangeButton(new Image(Assets.Environment.TILES_SEWERS, 48, 96, 16, 16), "炼金",
                "\n\n" + "_-_ 近战武器也能分解成液金"+
                        "\n\n" + "_-_ 诅咒棱晶获取减少。"+
                        "\n\n" + "_-_ 羽落晶核现在会自动释放。"+
                        "\n\n" + "_-_ 虹卫将敌人从玩家视野驱赶后，会回到玩家身边。"+
                        "\n\n" +"_-_ 沉沦单位会紧跟这英雄，并能追随到下一层"
        ));
        changes.addButton(new ChangeButton(Icons.get(Icons.PREFS), "杂项",
                "_-_ 等待后攻击必定命中"+
                        "\n\n" +"_-_ 敌人和友军单位也能进行伏击，玩家误伤自己被视为伏击"+
                        "\n\n" +"_-_ 被击退的单位碰撞到墙壁后，会根据未实现的击退距离造成伤害。"+
                        "\n\n" +"_-_ 诅咒陷阱会随机附魔英雄装备的物品，并会覆盖附魔和刻印。"+
                        "\n\n" +"_-_ 变形符石暂时代替强化符石"+
                        "\n\n" +"_-_ 扔出任何道具都会解除隐身（喝药依旧不会）"+
                        "\n\n" + "_-_ 暂时移除三钥匙房、献祭房。"));

        changes = new ChangeInfo("全新内容", false, null);
        changes.hardlight(Window.TITLE_COLOR);
        changeInfos.add(changes);
        changes.addButton(new ChangeButton(new Image(Assets.Sprites.ADVENTURER, 0, 90, 12, 15), "新角色：冒险家",
                "冒险家各项全能，携带着能提供额外等级的祝福宝珠。（未完工）"
        ));
        changes.addButton(new ChangeButton(new Image(Assets.Sprites.DUELIST, 0, 90, 12, 15), "新角色：决斗家",
                "决斗家是武器大师，他携带着一把能追击敌人的备用短剑。（未完工）"
        ));
        changes.addButton(new ChangeButton(new ItemSprite(ItemSpriteSheet.LOCKED_CHEST), "珍宝",
                " _-_ 地牢中的金箱现在有极低概率开出珍宝，珍宝可能是近战武器、投掷武器、卷轴，或是其他任何类型的装备或道具。"+
                        "\n\n" + "_-_ 珍宝往往具有比其他道具更强大或更特殊的能力，甚至可能影响到整场游戏的运营。"+
                        "\n\n" + "_-_ 珍宝不会通过其他方式生成，并且无法被破坏。"
        ));
        changes.addButton(new ChangeButton(new Image(new StatueSprite()), "魔像",
                "_-_ 现在的魔像不再使用携带的武器，但会携带特殊能力。魔像有更高几率携带特殊武器。"+
                        "\n" + "_-_ 追猎魔像：它身上的机关运转速度出奇的快！任何被锁定为目标的人都难以轻易逃脱它的追击。" +
                        "\n" + "_-_ 掌控魔像：这种石像具有强大的控制力，曾用于护送重要人物。石拳上安装了冲击装置，能击退靠近的敌人，而腰间的锁链能将远处的敌人拉回。" +
                        "\n" + "_-_ 守卫魔像：巨大的体型和厚重的外壳让其常用于镇守领地，想要击毁它显然得花上一点功夫。" +
                        "\n" + "_-_ 爆炸魔像：构成躯壳的爆炸黏土令你瞬间意识到它的用途，那就是疯狂的毁灭！" +
                        "\n" + "_-_ 暴怒魔像：矮人工匠的一次大胆尝试，坚韧强大但无法掌控。曾被质疑毫无用处，直到某次运送魔像的队伍被逼入绝境……" +
                        "\n" + "_-_ 恐慌魔像：原型是被设计用于侦察的的机关石像，被敌人发现时会爆发一整刺眼的光芒。不知怎的，你能从本应毫无生机的石像中感受到一丝恐慌。"
        ));
    }
}
