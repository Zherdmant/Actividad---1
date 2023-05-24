package actividad.CAIProgram;

import java.security.SecureRandom;
import java.util.Scanner;

/**
 * Clase que implementa un programa de instrucción asistida por computadora
 * (CAI) para operaciones aritméticas básicas.
 */
public class CAIProgram {
    // Variables de instancia
    private Scanner scanner; // Objeto Scanner para la entrada del usuario
    private SecureRandom random; // Objeto SecureRandom para generar números aleatorios

    private int correctAnswers; // Número de respuestas correctas
    private int incorrectAnswers; // Número de respuestas incorrectas
    private int totalQuestions; // Total de preguntas a realizar
    private int difficultyLevel; // Nivel de dificultad elegido por el usuario
    private int problemType; // Tipo de problema aritmético elegido por el usuario
    private int num1; // Primer número en la pregunta
    private int num2; // Segundo número en la pregunta
    private char operator; // Operador aritmético en la pregunta
    private int correctAnswer; // Respuesta correcta de la pregunta
    private String question; // Pregunta aritmética generada
    private int userAnswer; // Respuesta proporcionada por el usuario
    private double percentageCorrect; // Porcentaje de respuestas correctas
    private String choice; // Opción elegida por el usuario para permitir que otro estudiante pruebe el
                           // programa

    /**
     * Método principal que inicia el programa.
     */
    public void start() {
        // Inicializar variables y objetos
        this.scanner = new Scanner(System.in);
        this.random = new SecureRandom();
        this.correctAnswers = 0;
        this.incorrectAnswers = 0;
        this.totalQuestions = 10;
        this.difficultyLevel = 0;
        this.problemType = 0;
        this.num1 = 0;
        this.num2 = 0;
        this.operator = '\0';
        this.correctAnswer = 0;
        this.question = "";
        this.userAnswer = 0;
        this.percentageCorrect = 0.0;
        this.choice = "";

        // Mensaje de bienvenida y selección de nivel de dificultad y tipo de problema
        System.out.println(
                "Bienvenido al programa de instrucción asistida por computadora (CAI) para operaciones aritméticas básicas.");
        System.out.print("Elige el nivel de dificultad (1 = números de un dígito, 2 = números de dos dígitos, etc.): ");
        this.difficultyLevel = this.scanner.nextInt();

        System.out.print(
                "Elige el tipo de problema aritmético (1 = suma, 2 = resta, 3 = multiplicación, 4 = división, 5 = mezcla aleatoria): ");
        this.problemType = this.scanner.nextInt();

        // Ciclo principal para realizar las preguntas
        while (correctAnswers < totalQuestions) {
            // Generar números y operador aleatorio para la pregunta
            this.num1 = this.generateRandomNumber();
            this.num2 = this.generateRandomNumber();
            this.operator = this.getOperator();

            // Calcular respuesta correcta y construir la pregunta
            this.correctAnswer = this.calculateAnswer();
            this.question = "¿Cuánto es " + this.num1 + " " + this.operator + " " + this.num2 + "?";
            System.out.println(this.question);

            // Obtener respuesta del usuario
            this.userAnswer = this.scanner.nextInt();

            // Verificar si la respuesta es correcta
            if (this.userAnswer == this.correctAnswer) {
                // Si la respuesta del usuario es igual a la respuesta correcta
                this.displayPositiveFeedback(); // Mostrar retroalimentación positiva
                this.correctAnswers++; // Incrementar el contador de respuestas correctas
            } else {
                // Si la respuesta del usuario es incorrecta
                this.displayNegativeFeedback(); // Mostrar retroalimentación negativa
                while (this.userAnswer != this.correctAnswer) {
                    // Mientras la respuesta del usuario siga siendo incorrecta
                    System.out.println("Intenta nuevamente:"); // Solicitar al usuario que vuelva a intentarlo
                    this.userAnswer = this.scanner.nextInt(); // Leer la nueva respuesta del usuario
                }
                this.displayPositiveFeedback(); // Mostrar retroalimentación positiva
                this.correctAnswers++; // Incrementar el contador de respuestas correctas
            }

            this.incorrectAnswers = this.correctAnswers - this.totalQuestions; // Calcular el número de respuestas
                                                                               // incorrectas
            this.percentageCorrect = (double) this.correctAnswers / this.totalQuestions * 100; // Calcular el porcentaje
                                                                                               // de respuestas
                                                                                               // correctas

            System.out.println("Respuestas correctas: " + this.correctAnswers); // Mostrar el número de respuestas
                                                                                // correctas
            System.out.println("Respuestas incorrectas: " + this.incorrectAnswers); // Mostrar el número de respuestas
                                                                                    // incorrectas
            System.out.printf("Porcentaje de respuestas correctas: %.2f%%\n", this.percentageCorrect); // Mostrar el
                                                                                                       // porcentaje de
                                                                                                       // respuestas
                                                                                                       // correctas

            if (this.percentageCorrect < 75) {
                // Si el porcentaje de respuestas correctas es menor que 75%
                System.out.println("Por favor, pide ayuda adicional a tu instructor."); // Mostrar un mensaje
                                                                                        // solicitando ayuda adicional
                                                                                        // al instructor
            } else {
                // Si el porcentaje de respuestas correctas es igual o mayor que 75%
                System.out.println("¡Felicidades, estás listo para pasar al siguiente nivel!"); // Mostrar un mensaje de
                                                                                                // felicitación
                System.out.println("¿Deseas permitir que otro estudiante pruebe el programa? (S/N)"); // Preguntar si se
                                                                                                      // desea permitir
                                                                                                      // que otro
                                                                                                      // estudiante
                                                                                                      // pruebe el
                                                                                                      // programa
                this.choice = scanner.next(); // Leer la elección del usuario
                if (!this.choice.equalsIgnoreCase("S")) {
                    // Si la elección no es "S" (ignorando mayúsculas/minúsculas)
                    break; // Salir del bucle
                }
                this.restart(); // Reiniciar el programa
            }

        }

        System.out.println("¡Felicidades! Has respondido correctamente a todas las preguntas.");

    }

    public void restart() {
        this.scanner = new Scanner(System.in); // Reiniciar el objeto Scanner para leer la entrada del usuario
        this.random = new SecureRandom(); // Reiniciar el objeto SecureRandom para generar números aleatorios
        this.correctAnswers = 0; // Reiniciar el contador de respuestas correctas a cero
        this.incorrectAnswers = 0; // Reiniciar el contador de respuestas incorrectas a cero
        this.totalQuestions = 10; // Establecer el número total de preguntas nuevamente a 10
        this.num1 = 0; // Reiniciar el primer número de la operación a cero
        this.num2 = 0; // Reiniciar el segundo número de la operación a cero
        this.operator = '\0'; // Reiniciar el operador de la operación a un valor nulo
        this.correctAnswer = 0; // Reiniciar la respuesta correcta a cero
        this.question = ""; // Reiniciar la pregunta a una cadena vacía
        this.userAnswer = 0; // Reiniciar la respuesta del usuario a cero
        this.percentageCorrect = 0.0; // Reiniciar el porcentaje de respuestas correctas a cero
        this.choice = ""; // Reiniciar la elección del usuario a una cadena vacía
    }

    public int generateRandomNumber() {
        int maxNumber = (int) Math.pow(10, this.difficultyLevel) - 1; // Calcula el número máximo basado en el nivel de
                                                                      // dificultad
        return 1 + this.random.nextInt(maxNumber); // Genera un número aleatorio entre 1 y el número máximo
    }

    public char getOperator() {
        switch (this.problemType) {
            case 1:
                return '+'; // Si el tipo de problema es 1, devuelve el operador de suma '+'
            case 2:
                return '-'; // Si el tipo de problema es 2, devuelve el operador de resta '-'
            case 3:
                return '*'; // Si el tipo de problema es 3, devuelve el operador de multiplicación '*'
            case 4:
                return '/'; // Si el tipo de problema es 4, devuelve el operador de división '/'
            case 5:
                char[] operators = { '+', '-', '*', '/' }; // Crea un arreglo de operadores
                int index = (int) (Math.random() * operators.length); // Genera un índice aleatorio dentro del rango del
                                                                      // arreglo
                return operators[index]; // Devuelve un operador aleatorio del arreglo
            default:
                return '+'; // Si el tipo de problema no coincide con ninguno de los casos anteriores,
                            // devuelve el operador de suma '+'
        }
    }

    // Define un método público llamado generateRandomOperator que devuelve un
    // operador aritmético
    public char generateRandomOperator() {
        // Declara un array de caracteres llamado "operators" con los operadores
        // aritméticos disponibles
        char[] operators = { '+', '-', '*', '/' };

        // Genera un número aleatorio entre 0 y la longitud del array de operadores
        int index = (int) (Math.random() * operators.length);

        // Devuelve el operador aritmético seleccionado aleatoriamente usando el índice
        // generado anteriormente
        return operators[index];
    }

    /**
     * Calcula y devuelve el resultado de una operación aritmética.
     * El tipo de operación se determina mediante el valor del operador.
     * Los operandos utilizados en el cálculo son num1 y num2, que se
     * obtienen del objeto actual.
     *
     * @return El resultado de la operación aritmética.
     */
    public int calculateAnswer() {
        int answer = 0; // Variable local para almacenar el resultado de la operación

        // Se utiliza un switch para determinar qué operación realizar
        switch (this.operator) {
            case '+': // Suma
                answer = this.num1 + this.num2;
                break;
            case '-': // Resta
                answer = this.num1 - this.num2;
                break;
            case '*': // Multiplicación
                answer = this.num1 * this.num2;
                break;
            case '/': // División
                answer = this.num1 / this.num2;
                break;
        }

        return answer; // Devuelve el resultado de la operación aritmética
    }

    /**
     * Muestra un mensaje de retroalimentación positiva de forma aleatoria.
     * La retroalimentación se selecciona de un conjunto predefinido de mensajes.
     * Utiliza un generador de números aleatorios para seleccionar el mensaje.
     */
    public void displayPositiveFeedback() {
        String[] positiveFeedback = { "¡Muy bien!", "¡Excelente!", "¡Buen trabajo!", "¡Sigue así!" };
        int index = this.random.nextInt(positiveFeedback.length);
        System.out.println(positiveFeedback[index]);
    }

    /**
     * Muestra un mensaje de retroalimentación negativa de forma aleatoria.
     * La retroalimentación se selecciona de un conjunto predefinido de mensajes.
     * Utiliza un generador de números aleatorios para seleccionar el mensaje.
     */
    public void displayNegativeFeedback() {
        String[] negativeFeedback = { "No. Por favor intenta de nuevo.", "Incorrecto. Intenta una vez más.",
                "¡No te rindas!", "No. Sigue intentando." };
        int index = this.random.nextInt(negativeFeedback.length);
        System.out.println(negativeFeedback[index]);
    }
}
