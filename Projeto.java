// Implementação do Singleton para o gerenciador de pagamentos
class PaymentManager {
    private static PaymentManager instance;

    private PaymentManager() {
        // Construtor privado para evitar instanciação direta
    }

    public static PaymentManager getInstance() {
        if (instance == null) {
            instance = new PaymentManager();
        }
        return instance;
    }

    public void processPayment(PaymentStrategy strategy, double amount) {
        strategy.pay(amount);
    }
}

// Interface Strategy para os diferentes métodos de pagamento
interface PaymentStrategy {
    void pay(double amount);
}

// Implementações concretas das estratégias de pagamento
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String expiryDate;
    private String cvv;

    public CreditCardPayment(String cardNumber, String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    @Override
    public void pay(double amount) {
        // Simulação de lógica de pagamento com cartão de crédito
        System.out.println("Pagamento de " + amount + " via cartão de crédito.");
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;
    private String password;

    public PayPalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public void pay(double amount) {
        // Simulação de lógica de pagamento com PayPal
        System.out.println("Pagamento de " + amount + " via PayPal.");
    }
}

// Facade para fornecer uma interface simplificada de pagamento
class PaymentFacade {
    private PaymentManager paymentManager;

    public PaymentFacade() {
        this.paymentManager = PaymentManager.getInstance();
    }

    public void processCreditCardPayment(String cardNumber, String expiryDate, String cvv, double amount) {
        PaymentStrategy strategy = new CreditCardPayment(cardNumber, expiryDate, cvv);
        paymentManager.processPayment(strategy, amount);
    }

    public void processPayPalPayment(String email, String password, double amount) {
        PaymentStrategy strategy = new PayPalPayment(email, password);
        paymentManager.processPayment(strategy, amount);
    }
}

// Exemplo de uso do padrão Facade com Singleton e Strategy
public class PaymentSystemExample {
    public static void main(String[] args) {
        // Utilização da Facade para realizar pagamentos
        PaymentFacade paymentFacade = new PaymentFacade();

        // Pagamento com cartão de crédito
        paymentFacade.processCreditCardPayment("1234 5678 9101 1121", "12/25", "123", 100.0);

        // Pagamento com PayPal
        paymentFacade.processPayPalPayment("example@example.com", "password", 50.0);
    }
}
