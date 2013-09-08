import javax.swing.*;

public class Caesar {
	enum RomanNumeralOnes {
		I, II, III, IV, V, VI, VII, VIII, IX, X
	};

	enum RomanNumeralTens {
		X, XX, XXX, XL, L, LX, LXX, LXXX, XC, C
	};

	enum RomanNumeralHundreds {
		C, CC, CCC, CD, D, DC, DCC, DCCC, CM, M
	};

	enum RomanNumeralThousands {
		M, MM, MMM, MMMM, MMMMM, MMMMMM, MMMMMMM, MMMMMMMM, MMMMMMMMM
	}

	RomanNumeralOnes CipherOnes;
	RomanNumeralTens CipherTens;
	RomanNumeralHundreds CipherHundreds;
	RomanNumeralThousands CipherThousands;

	public static void main(String[] args) {

		ImageIcon caesarIcon = new ImageIcon("caesarspeaks.gif");
		int start;
		JFrame frame = new JFrame("Le Converter");
		frame.setSize(400, 150);
		Object startOptions[] = { "Roman", "Arabic", "Cancel" };

		while (true) {
			int isRoman = 0;
			start = JOptionPane
					.showOptionDialog(
							frame,
							"Hello. I am Caesar. \nI can take any one of your Arabic numerals and convert it to Roman numberals. And, if you wish, vice versa. \nWhich numeral "
									+ "do you wish to convert to?",
							"Caesar Converter",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.PLAIN_MESSAGE, caesarIcon,
							startOptions, 3);
			switch (start) {
			case 0:
				isRoman = 1;
				break;
			case 1:
				isRoman = 2;
				break;
			default:
				JOptionPane.showMessageDialog(frame, "Away with ye.",
						"Caesar Converter", JOptionPane.PLAIN_MESSAGE,
						caesarIcon);
				System.exit(0);
			}

			String output = ""; // use this to store your answer

			// String answer = input.next();

			if (isRoman == 1) {
				String arabicInputValue = (String) JOptionPane
						.showInputDialog(
								frame,
								"Good choice. Now, what is the arabic numeral you want converted?",
								"Caesar", JOptionPane.INFORMATION_MESSAGE,
								caesarIcon, null, null);

				boolean arabicchecker;
				arabicchecker = true;
				int answer2 = 0;

				try {
					answer2 = new Integer(arabicInputValue);

					String arabic = "";
					arabic += answer2;
					// .thousands
					int numdig = arabic.length();
					Integer ones = Integer.parseInt(arabic.substring(
							numdig - 1, numdig));
					Integer tens = 0;
					if (numdig > 1)
						tens = Integer.parseInt(arabic.substring(numdig - 2,
								numdig - 1));
					Integer hundreds = 0;
					if (numdig > 2)
						hundreds = Integer.parseInt(arabic.substring(
								numdig - 3, numdig - 2));
					int thousands = 0;
					if (numdig > 3)
						thousands = Integer.parseInt(arabic.substring(
								numdig - 4, numdig - 3));

					for (int i = 1; i <= thousands; i++) {
						output += "M";
					}
					// .hundreds

					int D = 0; // test for less than, equal to, or greater than
								// 500
					D = hundreds.compareTo(5);
					switch (D) {
					case 1:
						if (hundreds == 9)
							output += "CM";
						else {
							output += "D";
							for (int i = 6; i <= hundreds; i++) {
								output += "C";
							}
						}
						break;
					case -1:
						if (hundreds == 4)
							output += "CD";
						else if (hundreds != 0)
							for (int i = 1; i <= hundreds; i++) {
								output += "C";
							}
						break;
					default:
						output += "D";
					}
					// tens

					int L = 0; // test for less than, equal to, or greater than
								// 50
					L = tens.compareTo(5);
					switch (L) {
					case 1:
						if (tens == 9)
							output += "XC";
						else {
							output += "L";
							for (int i = 6; i <= tens; i++) {
								output += "X";
							}
						}
						break;
					case -1:
						if (tens == 4)
							output += "XL";
						else if (tens != 0)
							for (int i = 1; i <= tens; i++) {
								output += "X";
							}
						break;
					default:
						output += "L";
					}
					// .ones

					int V = 0; // test for less than, equal to, or greater than
								// 5
					V = ones.compareTo(5);
					switch (V) {
					case 1:
						if (ones == 9)
							output += "IX";
						else {
							output += "V";
							for (int i = 6; i <= ones; i++) {
								output += "I";
							}
						}
						break;
					case -1:
						if (ones == 4)
							output += "IV";
						else if (ones != 0)
							for (int i = 1; i <= ones; i++) {
								output += "I";
							}
						break;
					default:
						output += "V";
					}
				} catch (Exception e) {
					System.out.println("Whoops");
					arabicchecker = false;
				}
				if (arabicchecker == false) {

					JOptionPane.showMessageDialog(frame,
							"That is not an arabic numeral!", "Inane Error",
							JOptionPane.ERROR_MESSAGE, caesarIcon);
				}

				else {

					String result = "The arabic number " + answer2
							+ " has been translated to: " + output
							+ ". Good day.";
					JOptionPane.showMessageDialog(frame, result,
							"Here is what you've been waiting for",
							JOptionPane.INFORMATION_MESSAGE, caesarIcon);
				}
			} else if (isRoman == 2) {
				String answer3 = (String) JOptionPane
						.showInputDialog(
								frame,
								"Well, if you insist. What is the roman numeral you want converted?",
								"Caesar", JOptionPane.INFORMATION_MESSAGE,
								caesarIcon, null, null);

				String answerStore = answer3;
				answer3 = answer3 + "i";
				int number = 0;
				boolean romanchecker;
				romanchecker = true;
				/*
				 * if(answer3.equalsIgnoreCase("M")){
				 * 
				 * System.out.println(
				 * "Your roman numeral M has been translated to: 1000. Have a nice day."
				 * ); }
				 */

				for (int j = 0; j < (answer3.length() - 1); j++) {
					if (convert(answer3.charAt(j)) < convert(answer3
							.charAt(j + 1))) {
						switch (answer3.charAt(j)) {
						case 'M':
							number -= 1000;
							break;
						case 'D':
							number -= 500;
							break;
						case 'C':
							number -= 100;
							break;
						case 'L':
							number -= 50;
							break;
						case 'X':
							number -= 10;
							break;
						case 'V':
							number -= 5;
							break;
						case 'I':
							number -= 1;
							break;
						default:
							break;
						}
					} else if (convert(answer3.charAt(j)) >= convert(answer3
							.charAt(j + 1))) {
						
						int intermediate = convert(answer3.charAt(j));
						if (intermediate == 0)
							romanchecker = false;
						else
							number += intermediate;

						if (romanchecker == false) {

							break;
						}

					}

				}
				if (romanchecker == false) {

					JOptionPane.showMessageDialog(frame,
							"That is not a roman numeral!", "Inane error",
							JOptionPane.ERROR_MESSAGE, caesarIcon);
				}

				else {

					String result = "The roman numeral " + answerStore
							+ " has been translated to: " + number
							+ ". Good day.";
					JOptionPane.showMessageDialog(frame, result,
							"Here is what you've been waiting for",
							JOptionPane.INFORMATION_MESSAGE, caesarIcon);
				}
			}
		}
	}

	public static int convert(char converted) {
		switch (converted) {
		case 'M':
			return 1000;
		case 'D':
			return 500;
		case 'C':
			return 100;
		case 'L':
			return 50;
		case 'X':
			return 10;
		case 'V':
			return 5;
		case 'I':
			return 1;
		default:
			return 0;
		}
	}
}