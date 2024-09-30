package application;

import java.util.Scanner;
import java.util.Locale;
import java.time.format.*;
import java.time.*;
import entities.*;
import services.*;

public class Program {
	public static void main(String[] args) {
	Locale.setDefault(Locale.US);
	Scanner sc = new Scanner(System.in);
	
	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	
	System.out.println("Entre com os dados do aluguel");
	System.out.print("Modelo do carro: ");
	String carModel = sc.nextLine();
	System.out.print("Retirada (dd/MM/yyyy hh:mm): ");
	LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
	System.out.print("Retorno (dd/MM/yyyy hh:mm): ");
	LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);
	
	CarRental cr = new CarRental(start, finish, new Vehicle(carModel));
	
	System.out.print("Entre com preço por hora: ");
	double pricePerHour = sc.nextDouble();
	System.out.print("Entre com preço por dia: ");
	double pricePerDay = sc.nextDouble();
	
	RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
	
	rentalService.processInvoice(cr);
	
	System.out.println("FATURA:");
	System.out.printf("Pagamento basico: %.2f%n", cr.getInvoice().getBasicPayment());
	System.out.printf("Imposto: %.2f%n", cr.getInvoice().getTax());
	System.out.printf("Pagamento total: %.2f%n", cr.getInvoice().getTotalPayment());
	
	sc.close();
	
	}
}
