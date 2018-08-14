
public class MathTest {
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			 float var = 1 - ((i / 1.03f) / (i + 1));
			 System.out.printf("%s score after %s iterations \n", var, i);
		}
	}
}