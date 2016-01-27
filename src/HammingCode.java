import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class HammingCode {
	
	public static void main(String [] args){
		
		//Read File that contains bits. 
		try{
			BufferedReader br = new BufferedReader(new FileReader("file.txt"));
			String bits;

			while((bits = br.readLine()) != null){
				System.out.println(bits);
				
			    int [] problem = bits.chars().map(c -> c-='0').toArray(); 
			 	
				System.out.println("The bits received are: ");
				
				for (int i = 0; i<problem.length; i++){
					System.out.print(problem[i]);
				}
				
				int parityBitsRequired = BitsRequired(4);
				
				int correct[] = correctErrors(problem, parityBitsRequired);
				
				
				System.out.println("\nGenerated Code");
				for (int i=correct.length-1; i>=0; i--){
					System.out.print(correct[i]);
				}
				
				System.out.println("\n\nFlip a bit");
				
				RandomflipBits(correct);
				
				for (int i=correct.length-1; i>=0; i--){
					System.out.print(correct[i]);
				}
				
				System.out.println("\n");
				
				correctBitsFlipping(correct);
				
				System.out.println("\nCorrected Generated Code: ");
				
				for (int i=correct.length-1; i>=0; i--){
					System.out.print(correct[i]);
				}
				
				System.out.println("\n");
			}
		
			br.close();
			
			}
			catch(IOException e){
				System.out.println("File I/O error!");
			}

		
		

		
	}

		//Number of Parity Bits.
		
		static int BitsRequired(int problemSize){
		
		int bitsSent =0, parityBits = 0;
		
		while(bitsSent<problemSize){
			if(Math.pow(2, parityBits) == bitsSent+parityBits+1){
				parityBits++;
			}
			else {
				bitsSent++;
			}	
		}
		return parityBits;
		}
		
		
		//Generate Hamming Code
		static int[] correctErrors(int problem[], int numBits){
			int corrected[] = new int[problem.length + numBits];
			int k = 0, j = 0; 
			
			for (int i=0; i<corrected.length; i++){
				
				if(i == Math.pow(2,k)-1){
					corrected[i] = 0;
					k++;
				}else{
				corrected[i] = problem[j];
				j++;
				}
			}
			
			j = 0;
			
			
			int countBits = 0, pNumber = 0, position = 1, i = 0;
			
			while(j < 3){
				k = 0;
				pNumber = (int) Math.pow(2, position)/2;
				i = pNumber-1;
				while(i <= corrected.length-1){
					

					if(corrected[i+k] == 1){
						countBits++;
					}
					
					k++;	
					
					if(k == pNumber){
						i = i+2*pNumber;
						k = 0;
					}
				}
				
				if(countBits % 2 == 0){
					corrected[pNumber-1] = 0;
				}else{
					corrected[pNumber-1] = 1;
				}		
			
			
				countBits = 0;

			j++;
			position++;

			}
	
			return corrected;
			
		}
		
		//Flip a bit
		static int[] RandomflipBits(int correct[]){
			
			Random rand = new Random();
			
			int  number = rand.nextInt(7) + 1;
						
			for (int i = 0; i<correct.length; i++){
				if (i == number-1) {
					if (correct[i] == 0){
						correct[i] = 1;
					}else{
						correct[i] = 0;
					} 
						
				}
			}
			
			return correct;	
			
		}
		
		//Correct bit flipped
		static int[] correctBitsFlipping(int correct[]){
			
			int countBits = 0, pNumber = 0, position = 1, i = 0, j=0, k=0, f = 0, Number = 0;
			int bitFlipped [] = new int[3];
			
			while(j < 3){
				k = 0;
				pNumber = (int) Math.pow(2, position)/2;
				i = pNumber-1;
				while(i <= correct.length-1){
					
					if(correct[i+k] == 1){
						countBits++;
					}
					
					k++;	
					
					if(k == pNumber){
						i = i+2*pNumber;
						k = 0;
					}
				}
								
				if (countBits%2 == 0){
					bitFlipped[f] = 0;
				}else{
					bitFlipped[f] = 1;
				}
				
				f++;
			
				countBits = 0;

			j++;
			position++;

			}
			
			System.out.println("The bit flipped was: ");
			for (int h=bitFlipped.length-1; h>=0; h--){
				System.out.print(bitFlipped[h]);
			
			}
			
			Number = convertToDecimal(bitFlipped) - 1;
			
			if (correct[Number] == 0){
				correct[Number] = 1;
			}else {
				correct[Number] = 0;
			}
			
			Number++;
			System.out.println(" = " + Number);
			
			return correct;
		}
		
		
		//Convert binary number to Decimal
		static int convertToDecimal(int binary[]){
			
			int decimal = 0;
			
			for (int i=0; i<binary.length; i++){
				
				decimal += binary[i]*Math.pow(2, i);	
			}
			
			return decimal;
			
		}
	}



