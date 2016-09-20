package bot;

import data.Status;
import util.Utils;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import org.tbot.internal.AbstractScript;
import org.tbot.internal.Manifest;
import org.tbot.internal.ScriptCategory;
import org.tbot.internal.event.listeners.PaintListener;
import org.tbot.methods.Time;

@Manifest(version = 1, name = "GetBest_Example", description = "look at this", category = ScriptCategory.OTHER, openSource = true, authors = "Poop")
public class Bot extends AbstractScript implements PaintListener, MouseListener {

    private long startingTime = 0;
    private boolean showPaint = true;
    public static Status STATUS;

    @Override
    public int loop() {
        switch (STATUS) {
            case GET_BEST:
                TaskExecutor.GET_BEST();
                break;
            case STOP_SCRIPT:
                return TaskExecutor.STOP_SCRIPT();
        }
        return 25;
    }

    @Override
    public boolean onStart() {
        startingTime = System.currentTimeMillis();
        STATUS = Status.GET_BEST;
        TaskExecutor.init();
        Time.sleep(3000);
        return true;
    }

    @Override
    public void onRepaint(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        int[] x = new int[7];
        if (showPaint) {
            Graphics2D gPaint = (Graphics2D) g.create();
            gPaint.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
            gPaint.setColor(Color.BLACK);
            //Draw the skill buttons
            gPaint.fillRect(6, 344, 51, 22 + x[0]);
            gPaint.fillRect(481, 344, 31, 22);
            gPaint.fillRect(6, 369, 507, 80);
            gPaint.fillRect(6, 452, 507, 22);
            Graphics2D gPaintText = (Graphics2D) g.create();
            gPaintText.setColor(Color.WHITE);
            //Label skill buttons
            gPaintText.drawString("Info", 15, 359);
            gPaintText.drawString("Hide", 484, 359);
            //Draw Information
            gPaintText.drawString("GetBest example - Credits to dude I forgot name for cool paint", 16, 402);
            gPaintText.drawString("Time running: " + Utils.getFormattedTime(System.currentTimeMillis() - startingTime), 10, 467);
            gPaintText.drawString("Status: " + STATUS, 152, 467);
            gPaintText.drawString("By Poop", 395, 467);
        } else {
            Graphics2D gPaint = (Graphics2D) g.create();
            gPaint.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
            gPaint.setColor(Color.BLACK);
            //Draw the "show" button
            gPaint.fillRect(481, 344, 31, 22);
            Graphics2D gPaintText = (Graphics2D) g.create();
            gPaintText.setColor(Color.WHITE);
            gPaintText.drawString("Show", 481, 359);
        }
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        if (Utils.isMouseClicked(481, 344, 31, 22) && showPaint) {
            arg0.consume();
            showPaint = false;
        } else if (Utils.isMouseClicked(481, 344, 31, 22)) {
            arg0.consume();
            showPaint = true;
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

}
