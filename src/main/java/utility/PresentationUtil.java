package utility;

import java.util.stream.IntStream;

public class PresentationUtil {

	public static void printSlowMotionImage(Integer[][] integers) {

		for (int i = 0; i < integers.length; i++) {
			for (int j = 0; j < integers[0].length; j++) {
				System.out.print(integers[i][j]);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (i < integers.length - 1)
				System.out.println();
		}
	}

	public static void printSlowMotionText(String text) {

		IntStream.range(0, text.length()).forEach(p -> {
			System.out.print(text.charAt(p));
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
}
