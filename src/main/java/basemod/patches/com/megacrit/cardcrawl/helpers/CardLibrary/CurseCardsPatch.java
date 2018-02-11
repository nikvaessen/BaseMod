package basemod.patches.com.megacrit.cardcrawl.helpers.CardLibrary;

import java.util.HashMap;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import basemod.BaseMod;

@SpirePatch(cls="com.megacrit.cardcrawl.helpers.CardLibrary", method="addCurseCards")
public class CurseCardsPatch {

	public static void Postfix() {
		// add new cards
		for (AbstractCard card : BaseMod.getCurseCardsToAdd()) {
			CardLibrary.add(card);
		}
		
		// remove old cards
		for (String card : BaseMod.getCurseCardsToRemove()) {
			// for some reason curses is set to private so we use reflection to access it
			Object cursesObj = BaseMod.getPrivateStatic(CardLibrary.class, "curses");
			HashMap<String, AbstractCard> curses = (HashMap<String, AbstractCard>) cursesObj;
			curses.remove(card);
			CardLibrary.curseCards--;
			CardLibrary.totalCardCount--;
		}
	}
}
