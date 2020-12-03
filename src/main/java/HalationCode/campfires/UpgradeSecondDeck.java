package HalationCode.campfires;

import HalationCode.effects.OtherCampfireSmithEffect;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;

public class UpgradeSecondDeck extends AbstractCampfireOption {

    public static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("halation:SimulatedSpireUpgrade");
    public static final String[] TEXT = uiStrings.TEXT;

    public UpgradeSecondDeck() {
        super();
        this.img = ImageMaster.CAMPFIRE_SMITH_BUTTON;
        this.usable = true;
        this.label = TEXT[0];
        this.description = TEXT[1];
    }

    public void useOption() {
        if (!this.usable) return;
        AbstractDungeon.effectList.add(new OtherCampfireSmithEffect());
    }
}
