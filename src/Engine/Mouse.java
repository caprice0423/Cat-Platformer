package Engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import GameObject.Rectangle;

public class Mouse {
	private static boolean isClicked;
	private static double x; // static --> belongs to the class itself instead of the instance
	private static double y;

	public static boolean isClicked() {
		return isClicked;
	}

	public static double getX() {
		return x;
	}

	public static double getY() {
		return y;
	}

	// X2 research
	public static boolean isInside(Rectangle r) {
		return (x > r.getX() && x < r.getX2()) && (y < r.getY2() && y > r.getY());

	}

	public static MouseListener getMouseListener() {
		return mouseListener;
	}

	private static final MouseListener mouseListener = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			isClicked = true;

			x = e.getX();
			y = e.getY();
			System.out.println(e.getX() + "," + e.getY());
			// System.out.println("test");

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			isClicked = false;
			// System.out.println("test");

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// System.out.println(e.getX() + "," + e.getY());

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	};

}
