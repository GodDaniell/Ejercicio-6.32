
import java.util.Scanner;

public class Simpletron extends SimpletronOperationCodes {

    private int[] memory = new int[1000];  // Modificación inciso a) - Extender la memoria a 1000 ubicaciones
    private int accumulator;
    private int instructionCounter;
    private int instructionRegister;
    private int operationCode;
    private int operand;
    private boolean run = true;

    // Método main para iniciar el programa
    public static void main(String[] args) {
        Simpletron simpletron = new Simpletron();  // Crear una instancia de Simpletron
        simpletron.run();  // Iniciar el simulador
    }

    /**
     * The <code>run</code> method will start Simpletron, display the welcome
     * message and then go straight into SML execution.
     */
    public void run() {
        welcomeMessage();
        execute();
    }

    private void welcomeMessage() {
        System.out.println("***            Welcome to Simpletron!           ***");
        System.out.println("*** Please enter your program, one instruction  ***");
        System.out.println("*** (or data word) at a time. I will display    ***");
        System.out.println("*** the location number and a question mark (?) ***");
        System.out.println("*** You then type the word for that location.   ***");
        System.out.println("*** Type -99999 to stop entering your program.  ***");
    }

    private void execute() {
        Scanner codeInputter = new Scanner(System.in);
        int instructionInput = 0;
        int memoryPointer = 0;

        do {
            //Output the code input prompt
            System.out.printf("%02d ? ", memoryPointer);
            instructionInput = Integer.parseInt(codeInputter.next(), 16);  // Aceptar entrada en hexadecimal
            memory[memoryPointer] = instructionInput;
            memoryPointer++;
        } while (instructionInput != -99999);

        System.out.printf("\n%s\n%s\n\n", "***  Program loading complete ***",
                "*** Program execution begins ***");

        while (run) {
            loadCode();
            operations(operationCode, operand);
        }

        System.exit(0);
    }

    private void loadCode() {
        instructionRegister = memory[instructionCounter];
        operationCode = instructionRegister / 100;
        operand = instructionRegister % 100;
    }

    private void operations(int operationCode, int operand) {
        boolean branching = false;

        switch (operationCode) {
            case READ:
                Scanner read = new Scanner(System.in);
                System.out.print("Enter a number: ");
                int number = read.nextInt();
                memory[operand] = number;
                break;

            case WRITE:
                System.out.println(memory[operand]);
                break;

            case LOAD:
                accumulator = memory[operand];
                break;

            case STORE:
                memory[operand] = accumulator;
                break;

            case ADD:
                accumulator += memory[operand];
                break;

            case SUBTRACT:
                accumulator -= memory[operand];
                break;

            case DIVIDE:
                if (memory[operand] == 0) {
                    System.out.printf("\n%s\n%s\n", "*** CANNOT DIVIDE BY ZERO ***", "*** EXITING NOW ***");
                    System.exit(-1);
                } else {
                    accumulator /= memory[operand];
                }
                break;

            case MULITPLY:
                accumulator *= memory[operand];
                break;

            case MOD:  // Cálculo de residuo (mod)
                accumulator %= memory[operand];
                break;

            case EXPONENTIATION:  // Exponenciación
                accumulator = (int) Math.pow(accumulator, memory[operand]);
                break;

            case BRANCH:
                instructionCounter = operand;
                branching = true;
                break;

            case BRANCHNEG:
                if (accumulator < 0) {
                    instructionCounter = operand;
                    branching = true;
                }
                break;

            case BRANCHZERO:
                if (accumulator == 0) {
                    instructionCounter = operand;
                    branching = true;
                }
                break;

            case HALT:
                System.out.println("Processing complete...");
                run = false;
                memoryDump();
                break;
        }

        if (!branching) {
            instructionCounter++;
        }
    }

    private void memoryDump() {
        int tens, ones;

        System.out.printf("\t%02d\t%02d\t%02d\t%02d\t%02d\t%02d\t%02d\t%02d\t%02d\t%02d\n", 0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        for (tens = 0; tens < 100; tens += 10) {
            System.out.printf("%02d\t", tens);
            for (ones = 0; ones < 10; ones++) {
                System.out.printf("%04X\t", memory[tens + ones]);  // Mostrar en hexadecimal
            }
            System.out.println();
        }
    }
}

