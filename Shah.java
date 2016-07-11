//6ah.needs:
//rokado,da izkarva edi koi si e check;i check mate
package uprajneniqOtKnijkata;

import java.util.Scanner;

public class Shah {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] black = { "Bp", "Br", "Bh", "Bb", "Bq",
				"Bk" };/*
						 * bp-black pawn-0,br-black rock-1,bh-black
						 * horse-2,bp-black bishop-3 ,bq-black queen-4, bk-black
						 * king-5
						 */
		String[] white = { "Wp", "Wr", "Wh", "Wb", "Wq",
				"Wk" };/*
						 * Wp-white pawn-0,Wr-white rock-1,Wh-white
						 * horse-2,Wp-white bishop-3 ,Wq-white queen-4, Wk-white
						 * king-5
						 */
		String[][] board = new String[9][9];
		setBoardBasic(board);// pravim duskata
		clearBoard(board);// iz4istvame null-ovete
		arr2dDisplay(board);// pokazvame board-a
		beginLayout(board, black, white);// set-vame boarda(narejdame figurite
		arr2dDisplay(board);
		int cnt = 0;// counter za tova koi e na hod i kolko hoda sa minali ;
		do {
			cnt++;
			System.out.println("turn " + cnt + " :");
			if (cnt % 2 == 1) {
				System.out.println("white's turn: ");
			} else {
				System.out.println("black's turn: ");
			}
			pieceToMove(black, cnt, white, board);
			arr2dDisplay(board);
		} while (true);
	}

	static void whereToMove(int x, int y, String[][] board, String str, String[] figWhite, String[] figBlack,
			String listOfPossibleMoves) {
		Scanner sc = new Scanner(System.in);
		int a, b;
		boolean swap = false;
		do {
			System.out.println("where do you want to move the figure(" + x + " ;" + y + ") ?");
			arr2dDisplay(board);
			System.out.println("x=");
			a = (int) (sc.next().charAt(0) - '0');
			System.out.println("y=");
			b = (int) (sc.next().charAt(0) - '0');
			if (a < 1 || b < 1 || a >= board.length
					|| b >= board.length) {/*
											 * izbranite koordinati na duskata
											 * li sa ?
											 */
				System.out.println("the place is not on the board,go again! ");
				continue;
			} else {// ako da
				for (int i = 0; i < listOfPossibleMoves
						.length(); i += 2) {/* ako da */
					if (a == (int) (listOfPossibleMoves.charAt(i)
							- 48)/*
									 * / koordinatite vuv vuzmojnite hodove li
									 * sa ?
									 */
							&& b == (int) (listOfPossibleMoves.charAt(i + 1) - 48)) {
						if (board[a][b].charAt(1) != 'k') {// ako da i na poleto
															// ne e car se
															// izvur6va hoda
							board[a][b] = board[x][y];
							board[x][y] = "  ";
							swap = true;

						}
					}
				}
			}
		} while (swap == false);
	}

	static void pieceToMove(String[] figBlack, int counter, String[] figWhite, String[][] board) {
		Scanner sc = new Scanner(System.in);
		String str;
		String listOfPossibleMoves = "";
		int x = 0, y = 0;
		do {
			System.out.println("what will you move ?");
			str = sc.next();
			str = str.toLowerCase();
			boolean figExists = false;
			for (int i = 0; i < figWhite.length; i++) {// cikul,tursi dali i koq
														// figura e izbrana za
														// igra
				if (counter % 2 == 1) {
					figExists = figWhite[i].toLowerCase().equals(str);
					if (figExists) {
						str = figWhite[i];
						break;
					}
				} else {
					figExists = figBlack[i].toLowerCase().equals(str);
					if (figExists) {
						str = figBlack[i];
						break;
					}
				}
			}
			if (!figExists) {// ako nqma takva figura,davai ot na4alo
				System.out.println("no such figure,go again!");
				continue;
			}
			boolean figInPlay = false;
			for (int i = 1; i < board.length; i++) {
				for (int j = 1; j < board.length; j++) {/*
														 * proverqvat dali
														 * slu4aino vupreki 4e
														 * ima takava figura ne
														 * sa vzeti vsi4ki kopiq
														 * ot neq
														 */
					if (board[i][j] == str) {
						figInPlay = true;
						break;
					}
				}
				if (figInPlay == true) {
					break;
				}
			}
			if (figInPlay == false) {// ako nqma ostanali takiva figuri na
										// duskata,davai
										// otna4alo
				System.out.println("no more such figures left in play,go again!");
				continue;
			}
			do {// kude se namira figurata koqto iska6 da igrae6
				arr2dDisplay(board);
				System.out.println("where is the " + str + " that you want to move ?");
				System.out.println("x=");
				x = (int) (sc.next().charAt(0) - '0');
				System.out.println("y=");
				y = (int) (sc.next().charAt(0) - '0');
				if ((x < 1 || y < 1 || x >= board.length || y >= board.length || board[x][y] != str)) {
					System.out.println("no such figure there,go again! ");
				}
			} while (x < 1 || y < 1 || x >= board.length || y >= board.length || board[x][y] != str);
			listOfPossibleMoves = possibleMove(x, y, str, figBlack, figWhite,
					board);/* /vzimame vsi4ki vuzmojni hodove na figurata */
		} while (listOfPossibleMoves.length() < 2);// ako ima takiva
		whereToMove(x, y, board, str, figWhite, figBlack, listOfPossibleMoves);
	}

	static String possibleMove(int x, int y, String str, String[] figBlack, String[] figWhite, String[][] board) {
		int temp = 0;
		String listMoves = "";// string paze6t vsi4ki vuzmojni hodove
		for (int i = 0; i < figWhite.length; i++) {// namira koq e figurata v
													// masiva ot figuri
			if (figWhite[i].contains(str)) {
				temp = i;
				break;
			}
			if (figBlack[i].contains(str)) {
				temp = i;
				break;
			}
		}
		switch (temp) {
		case 0:// pawn
			listMoves = pawnPossibleMoves(x, y, board, str);
			break;
		case 1:// rock
			listMoves = rockPossibleMoves(x, y, board, str);
			break;
		case 2:// horse/knight
			listMoves = knightPossibleMoves(x, y, board, str);
			break;
		case 3:// bishop
			listMoves = bishopPossibleMoves(x, y, board, str);
			break;
		case 4:// queen
			listMoves = bishopPossibleMoves(x, y, board, str);
			listMoves = listMoves + rockPossibleMoves(x, y, board, str);
			break;
		case 5:// king
			listMoves = kingPossibleMoves(x, y, board, str);
			break;
		}
		System.out.println("there are " + (listMoves.length() / 2) + " possible moves with that figure");
		return listMoves;
	}

	static String kingPossibleMoves(int x, int y, String[][] board,
			String str) {/*
							 * / da se slojat dopalnitelni proverki
							 */
		String listOfPossibleMoves = "";
		int i = 1;
		if (x + i < board.length && y + i < board.length) {
			if (board[x + i][y + i].contains("  ")) {
				listOfPossibleMoves = listOfPossibleMoves + (x + i);
				listOfPossibleMoves = listOfPossibleMoves + (y + i);
			} else if (str.charAt(0) != board[x + i][y + i].charAt(0)) {
				listOfPossibleMoves = listOfPossibleMoves + (x + i);
				listOfPossibleMoves = listOfPossibleMoves + (y + i);
			}
		}
		if (x + i < board.length && y - i > 0) {// analogi4no na
												// upright no za
												// upLeft
			if (board[x + i][y - i].contains("  ")) {
				listOfPossibleMoves = listOfPossibleMoves + (x + i);
				listOfPossibleMoves = listOfPossibleMoves + (y - i);
			} else if (str.charAt(0) != board[x + i][y - i].charAt(0)) {
				listOfPossibleMoves = listOfPossibleMoves + (x + i);
				listOfPossibleMoves = listOfPossibleMoves + (y - i);
			}
		}
		if (x - i > 0 && (y + i) < board.length) {// analogi4no
													// kato
													// upRght
			if (board[x - i][y + i].contains("  ")) {
				listOfPossibleMoves = listOfPossibleMoves + (x - i);
				listOfPossibleMoves = listOfPossibleMoves + (y + i);
			} else if (str.charAt(0) != board[x - i][y + i].charAt(0)) {
				listOfPossibleMoves = listOfPossibleMoves + (x - i);
				listOfPossibleMoves = listOfPossibleMoves + (y + i);
			}
		}
		if (x - i > 0 && (y - i) > 0) {// analogi4no na up no za
										// downleft
			if (board[x - i][y - i].contains("  ")) {
				listOfPossibleMoves = listOfPossibleMoves + (x - i);
				listOfPossibleMoves = listOfPossibleMoves + (y - i);
			} else if (str.charAt(0) != board[x - i][y - i].charAt(0)) {
				listOfPossibleMoves = listOfPossibleMoves + (x - i);
				listOfPossibleMoves = listOfPossibleMoves + (y - i);
			}
		}

		if (x + i < board.length) {// up
			if (board[x + i][y].contains("  ")) {// ako e nezaeto
													// poleto
				listOfPossibleMoves = listOfPossibleMoves + (x + i);
				listOfPossibleMoves = listOfPossibleMoves + (y);
			} else if (str.charAt(0) != board[x + i][y]
					.charAt(0)) {/*
									 * ako ima 4ujda figura
									 */
				listOfPossibleMoves = listOfPossibleMoves + (x + i);
				listOfPossibleMoves = listOfPossibleMoves + (y);
			}
		}
		if (x - i > 0) {// analogi4no na up no za down
			if (board[x - i][y].contains("  ")) {
				listOfPossibleMoves = listOfPossibleMoves + (x - i);
				listOfPossibleMoves = listOfPossibleMoves + (y);
			} else if (str.charAt(0) != board[x - i][y].charAt(0)) {
				listOfPossibleMoves = listOfPossibleMoves + (x - i);
				listOfPossibleMoves = listOfPossibleMoves + (y);
			}
		}
		if ((y + i) < board.length) {// analogi4no kato up
			// no za right
			if (board[x][y + i].contains("  ")) {
				listOfPossibleMoves = listOfPossibleMoves + (x);
				listOfPossibleMoves = listOfPossibleMoves + (i + y);
			} else if (str.charAt(0) != board[x][y + i].charAt(0)) {
				listOfPossibleMoves = listOfPossibleMoves + (x);
				listOfPossibleMoves = listOfPossibleMoves + (i + y);
			}
		}
		if ((y - i) > 0) {// analogi4no na up no za left
			if (board[x][y - i].contains("  ")) {
				listOfPossibleMoves = listOfPossibleMoves + (x);
				listOfPossibleMoves = listOfPossibleMoves + (y - i);
			} else if (str.charAt(0) != board[x][y - i].charAt(0)) {
				listOfPossibleMoves = listOfPossibleMoves + (x);
				listOfPossibleMoves = listOfPossibleMoves + (y - i);
			}
		}
		return listOfPossibleMoves;
	}

	static String bishopPossibleMoves(int x, int y, String[][] board,
			String str) {/*
							 * / number of possible moves with the // bishop
							 */
		String listOfPossibleMoves = "";

		boolean upRight = true, downRight = true, downLeft = true,
				upLeft = true;/*
								 * moje li da hodi v tezi posoki
								 */
		for (int i = 1; i < board.length; i++) {
			if (x + i < board.length && y + i < board.length && upRight) {
				if (board[x + i][y + i].contains("  ")) {
					listOfPossibleMoves = listOfPossibleMoves + (x + i);
					listOfPossibleMoves = listOfPossibleMoves + (y + i);
				} else if (str.charAt(0) != board[x + i][y + i].charAt(0)) {
					listOfPossibleMoves = listOfPossibleMoves + (x + i);
					listOfPossibleMoves = listOfPossibleMoves + (y + i);
					upRight = false;
				} else if (str.charAt(0) == board[x + i][y + i].charAt(0)) {
					upRight = false;
				}
			}
			if (x + i < board.length && y - i > 0 && upLeft) {// analogi4no na
																// upright no za
																// upLeft
				if (board[x + i][y - i].contains("  ")) {
					listOfPossibleMoves = listOfPossibleMoves + (x + i);
					listOfPossibleMoves = listOfPossibleMoves + (y - i);
				} else if (str.charAt(0) != board[x + i][y - i].charAt(0)) {
					listOfPossibleMoves = listOfPossibleMoves + (x + i);
					listOfPossibleMoves = listOfPossibleMoves + (y - i);
					upLeft = false;
				} else if (str.charAt(0) == board[x + i][y - i].charAt(0)) {
					upLeft = false;
				}
			}
			if (x - i > 0 && (y + i) < board.length && downRight) {// analogi4no
																	// kato
																	// upRght
				if (board[x - i][y + i].contains("  ")) {
					listOfPossibleMoves = listOfPossibleMoves + (x - i);
					listOfPossibleMoves = listOfPossibleMoves + (y + i);
				} else if (str.charAt(0) != board[x - i][y + i].charAt(0)) {
					listOfPossibleMoves = listOfPossibleMoves + (x - i);
					listOfPossibleMoves = listOfPossibleMoves + (y + i);
					downRight = false;
				} else if (str.charAt(0) == board[x - i][y + i].charAt(0)) {
					downRight = false;
				}
			}
			if (x - i > 0 && (y - i) > 0 && downLeft) {// analogi4no na up no za
														// downleft
				if (board[x - i][y - i].contains("  ")) {
					listOfPossibleMoves = listOfPossibleMoves + (x - i);
					listOfPossibleMoves = listOfPossibleMoves + (y - i);
				} else if (str.charAt(0) != board[x - i][y - i].charAt(0)) {
					listOfPossibleMoves = listOfPossibleMoves + (x - i);
					listOfPossibleMoves = listOfPossibleMoves + (y - i);
					downLeft = false;
				} else if (str.charAt(0) == board[x - i][y - i].charAt(0)) {
					downLeft = false;
				}
			}

			if (upLeft == false && upRight == false && downLeft == false && downRight == false) {
				break;
			}
		}
		return listOfPossibleMoves;
	}

	static String knightPossibleMoves(int x, int y, String[][] board,
			String str) {/*
							 * number of possible moves with the knight
							 */
		String listOfPossibleMoves = listOfPossibleMoves = "";
		if (x + 2 < board.length && (y + 1 < board.length)) {
			if (board[x + 2][y + 1].contains("  ") || board[x + 2][y + 1].charAt(0) != str.charAt(0)) {
				listOfPossibleMoves = listOfPossibleMoves + (x + 2);
				listOfPossibleMoves = listOfPossibleMoves + (y + 1);
			}
		}
		if (x + 2 < board.length && y - 1 > 0) {
			if (board[x + 2][y - 1].contains("  ") || board[x + 2][y - 1].charAt(0) != str.charAt(0)) {
				listOfPossibleMoves = listOfPossibleMoves + (x + 2);
				listOfPossibleMoves = listOfPossibleMoves + (y - 1);
			}
		}
		if (x - 2 > 0 && y + 1 < board.length) {
			if (board[x - 2][y + 1].contains("  ") || board[x - 2][y + 1].charAt(0) != str.charAt(0)) {
				listOfPossibleMoves = listOfPossibleMoves + (x - 2);
				listOfPossibleMoves = listOfPossibleMoves + (y + 1);
			}
		}
		if (x - 2 > 0 && y - 1 > 0) {
			if (board[x - 2][y - 1].contains("  ") || board[x - 2][y - 1].charAt(0) != str.charAt(0)) {
				listOfPossibleMoves = listOfPossibleMoves + (x - 2);
				listOfPossibleMoves = listOfPossibleMoves + (y - 1);
			}
		}
		if (x - 1 > 0 && y - 2 > 0) {
			if (board[x - 1][y - 2].contains("  ") || board[x - 1][y - 2].charAt(0) != str.charAt(0)) {
				listOfPossibleMoves = listOfPossibleMoves + (x - 1);
				listOfPossibleMoves = listOfPossibleMoves + (y - 2);
			}
		}
		if (x - 1 > 0 && y + 2 < board.length) {
			if (board[x - 1][y + 2].contains("  ") || board[x - 1][y + 2].charAt(0) != str.charAt(0)) {
				listOfPossibleMoves = listOfPossibleMoves + (x - 1);
				listOfPossibleMoves = listOfPossibleMoves + (y + 2);
			}
		}
		if (x + 1 < board.length && y - 2 > 0)
			if (board[x + 1][y - 2].contains("  ") || board[x + 1][y - 2].charAt(0) != str.charAt(0)) {
				listOfPossibleMoves = listOfPossibleMoves + (x + 1);
				listOfPossibleMoves = listOfPossibleMoves + (y - 2);
			}
		if (x + 1 < board.length && y + 2 < board.length) {
			if (board[x + 1][y + 2].contains("  ") || board[x + 1][y + 2].charAt(0) != str.charAt(0)) {
				listOfPossibleMoves = listOfPossibleMoves + (x + 1);
				listOfPossibleMoves = listOfPossibleMoves + (y + 2);
			}
		}
		return listOfPossibleMoves;
	}

	static String rockPossibleMoves(int x, int y, String[][] board,
			String str) {/*
							 * number of possible moves with the rock
							 */
		String listOfPossibleMoves = "";
		boolean up = true, down = true, left = true,
				right = true;/*
								 * moje li da hodi v tezi posoki
								 */
		for (int i = 1; i < board.length; i++) {// up and down
			if (x + i < board.length && up) {// up
				if (board[x + i][y].contains("  ")) {// ako e nezaeto
														// poleto
					listOfPossibleMoves = listOfPossibleMoves + (x + i);
					listOfPossibleMoves = listOfPossibleMoves + (y);
				} else if (str.charAt(0) != board[x + i][y]
						.charAt(0)) {/*
										 * ako ima 4ujda figura
										 */
					listOfPossibleMoves = listOfPossibleMoves + (x + i);
					listOfPossibleMoves = listOfPossibleMoves + (y);
					// moje da q vzeme
					up = false;// no ne moje da se dviji pove4e v tazi
								// posoka
				} else if (str.charAt(0) == board[x + i][y]
						.charAt(0)) {/*
										 * ako ima figura ot su6tiq cvqt ne moje
										 * da se dviji pove4e
										 */
					up = false;
				}
			}
			if (x - i > 0 && down) {// analogi4no na up no za down
				if (board[x - i][y].contains("  ")) {
					listOfPossibleMoves = listOfPossibleMoves + (x - i);
					listOfPossibleMoves = listOfPossibleMoves + (y);
				} else if (str.charAt(0) != board[x - i][y].charAt(0)) {
					listOfPossibleMoves = listOfPossibleMoves + (x - i);
					listOfPossibleMoves = listOfPossibleMoves + (y);
					down = false;
				} else if (str.charAt(0) == board[x - i][y].charAt(0)) {
					down = false;
				}
			}
			if ((y + i) < board.length && right) {// analogi4no kato up
				// no za right
				if (board[x][y + i].contains("  ")) {
					listOfPossibleMoves = listOfPossibleMoves + (x);
					listOfPossibleMoves = listOfPossibleMoves + (y + i);
				} else if (str.charAt(0) != board[x][y + i].charAt(0)) {
					listOfPossibleMoves = listOfPossibleMoves + (x);
					listOfPossibleMoves = listOfPossibleMoves + (y + i);
					right = false;
				} else if (str.charAt(0) == board[x][y + i].charAt(0)) {
					right = false;
				}
			}
			if ((y - i) > 0 && left) {// analogi4no na up no za left
				if (board[x][y - i].contains("  ")) {
					listOfPossibleMoves = listOfPossibleMoves + (x);
					listOfPossibleMoves = listOfPossibleMoves + (y - i);
				} else if (str.charAt(0) != board[x][y - i].charAt(0)) {
					listOfPossibleMoves = listOfPossibleMoves + (x);
					listOfPossibleMoves = listOfPossibleMoves + (y - i);
					left = false;
				} else if (str.charAt(0) == board[x][y - i].charAt(0)) {
					left = false;
				}
			}
			if (up == false && down == false && right == false && left == false) {
				break;
			}
		}
		return listOfPossibleMoves;
	}

	static String pawnPossibleMoves(int x, int y, String[][] board,
			String str) {/*
							 * / number // of // possible // moves // with //
							 * the // pawn
							 */
		String listMoves = "";
		if (str.charAt(0) == 'W') {// ako e bqla;
			if (x == 2) {// ako ne e mestena
				if (board[x + 1][y].contains("  ")) {// i nqma ni6to pred
					// neq moje da se
					// mesti
					listMoves = listMoves + (x + 1);
					listMoves = listMoves + y;
					if (board[x + 2][y].contains(" ")) {// i nqma ni6to pred
						// neq moje da se
						// mesti
						listMoves = listMoves + (x + 2);
						listMoves = listMoves + y;
					}
				}
			} else if (x + 1 < board.length && board[x + 1][y].contains(
					" ")) {/*
							 * ako // e // mestena // i // nqma // ni6to //
							 * prede // neq // moje // da // se // mesti
							 */
				listMoves = listMoves + (x + 1);
				listMoves = listMoves + y;
			}
			if (x + 1 < board.length && y + 1 < board.length && board[x + 1][y + 1].charAt(0) == 'B') {
				/*
				 * moje li da vzima
				 */
				listMoves = listMoves + (x + 1);
				listMoves = listMoves + (y + 1);
			}
			if (x + 1 < board.length && y - 1 > 0 && board[x + 1][y - 1]
					.charAt(0) == 'B') {/*
										 * moje li da vzima
										 */
				listMoves = listMoves + (x + 1);
				listMoves = listMoves + (y + 1);
			}
		}
		if (str.charAt(0) == 'B') {// ako e 4erna
			if (x == 7) {// ako ne e mestena
				if (board[x - 1][y].contains("  ")) {
					listMoves = listMoves + (x - 1);
					listMoves = listMoves + (y);
					if (board[x - 2][y].contains(" ")) {
						listMoves = listMoves + (x - 2);
						listMoves = listMoves + (y);
					}
				}
			} else if (x - 1 > 0 && board[x - 1][y].contains(" ")) {// ako e
				// mestena
				listMoves = listMoves + (x - 1);
				listMoves = listMoves + (y);
			}
			if (x - 1 > 0 && y + 1 < board.length && board[x - 1][y + 1]
					.charAt(0) == 'W') {/*
										 * / ima // li // kakvo // da // vzima
										 */
				listMoves = listMoves + (x - 1);
				listMoves = listMoves + (y + 1);
			}
			if (x - 1 > 0 && y - 1 > 0 && board[x - 1][y - 1].charAt(0) == 'W') {
				listMoves = listMoves + (x - 1);
				listMoves = listMoves + (y - 1);
			}
		}
		return listMoves;
	}

	static void beginLayout(String[][] board, String[] figBlack, String[] figWhite) {// narejda
																						// duskata

		board[1][1] = figWhite[1];
		board[1][8] = figWhite[1];
		board[8][1] = figBlack[1];
		board[8][8] = figBlack[1];
		board[1][2] = figWhite[2];
		board[1][7] = figWhite[2];
		board[8][2] = figBlack[2];
		board[8][7] = figBlack[2];
		board[1][3] = figWhite[3];
		board[1][6] = figWhite[3];
		board[8][3] = figBlack[3];
		board[8][6] = figBlack[3];
		board[1][4] = figWhite[4];
		board[8][4] = figBlack[4];
		board[1][5] = figWhite[5];
		board[8][5] = figBlack[5];
		for (int i = 1; i < board.length; i++) {
			for (int j = 1; j < board.length; j++) {
				if (i == 2) {
					board[i][j] = figWhite[0];
				}
				if (i == 7) {
					board[i][j] = figBlack[0];
				}
			}
		}
	}

	static void setBoardBasic(String[][] board) {// pravi duskata s nadpisi
													// otstrani
		for (int i = 1; i < board.length; i++) {
			char bukva = 'a';
			bukva = (char) ('a' + i - 1);
			board[0][i] = Character.toString(bukva); // (a b c d e f g h );
			board[0][i] += " ";
			board[i][0] = Character.toString((char) ('0' + i));
		}
	}

	static void clearBoard(String[][] board) {// zapulva duskata sus " " 2
												// space-a i premahva
												// null-4etata

		board[0][0] = " ";
		for (int i = 1; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (j != 0) {
					board[i][j] = "  ";
				}
			}
		}

	}

	static void arr2dDisplay(String[][] arr) {
		for (int i = arr.length - 1; i >= 0; i--) {
			System.out.println("--------------------------------------------");
			for (int j = 0; j < arr.length; j++) {
				System.out.print(arr[i][j] + " | ");
			}
			System.out.println();

		}
		System.out.println("--------------------------------------------");
		System.out.println("  | 1  | 2  | 3  | 4  | 5  | 6  | 7  | 8     ");
		System.out.println("==============================================\n");
	}
}
