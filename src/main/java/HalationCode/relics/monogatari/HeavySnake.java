package HalationCode.relics.monogatari;

import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.relics.BetterOnSmithRelic;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import java.util.ArrayList;

public class HeavySnake extends CustomRelic implements BetterOnSmithRelic {
    public static final String ID = "halation:HeavySnake";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/HeavySnake.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;
    public static ArrayList<AbstractCard> cardsToShow = new ArrayList<>();
    private static int CURSE_AMT = 2;
    private static int UPGRADE_AMT = 3;

    public HeavySnake() {
        super(ID, IMG, RelicTier.BOSS, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + CURSE_AMT + DESCRIPTIONS[1] + UPGRADE_AMT + DESCRIPTIONS[2];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new HeavySnake();
    }

    @Override
    public void atBattleStartPreDraw() {
        for (int i = 0; i < CURSE_AMT; i++) {
            AbstractCard c = AbstractDungeon.returnRandomCurse();
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(c, 1, false, false, true));
        }
    }

    public void betterOnSmith(AbstractCard ca) {
        this.flash();
        for (int i = 0; i < UPGRADE_AMT; i++) {
            if (p.masterDeck.getUpgradableCards().isEmpty()) {
                return;
            }
            AbstractCard c = p.masterDeck.getUpgradableCards().getRandomCard(true);
            c.upgrade();
            cardsToShow.add(c);
        }
    }

    public static void cardEffects() {
        for (AbstractCard c : cardsToShow) {
            final float x = MathUtils.random(0.3f, 0.8f) * Settings.WIDTH;
            final float y = MathUtils.random(0.3f, 0.9f) * Settings.HEIGHT;
            AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(c.makeStatEquivalentCopy(), x, y));
            AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(x, y));
        }
        cardsToShow.clear();
    }

}
