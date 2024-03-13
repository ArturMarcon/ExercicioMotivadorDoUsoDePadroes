public class App {
    public static void main(String[] args) throws Exception {

        Carro basico = new Carro("Basico", TipoCombustivel.GASOLINA, 10, 55);
        Carro esportivo = new Carro("Esportivo", TipoCombustivel.GASOLINA, 6, 45);
        Carro utilitario = new Carro("Utilitario", TipoCombustivel.DIESEL, 5, 70);
        Carro suv = new Carro("SUV", TipoCombustivel.GASOLINA, 8, 55);
        Carro suvflex = new Carro("SUVFlex", TipoCombustivel.FLEX, 8, 6, 65);
        Carro econo = new Carro("Econo", TipoCombustivel.GASOLINA, 55, 20, 1, 200, 10);

        System.out.println("Tipos de veiculos:");
        System.out.println(basico);
        System.out.println(esportivo);
        System.out.println(utilitario);
        System.out.println(suv);
        System.out.println(suvflex);
        System.out.println(econo);


        System.out.println("\n\n----------------");
        System.out.println("\nAbastencendo carros");
        basico.abastece(TipoCombustivel.GASOLINA, 55);
        esportivo.abastece(TipoCombustivel.GASOLINA, 45);
        utilitario.abastece(TipoCombustivel.DIESEL, 70);
        suv.abastece(TipoCombustivel.GASOLINA, 55);
        suvflex.abastece(TipoCombustivel.ALCOOL, 65);
        econo.abastece(TipoCombustivel.GASOLINA, 55);
        System.out.println(basico);
        System.out.println(esportivo);
        System.out.println(utilitario);
        System.out.println(suv);
        System.out.println(suvflex);
        System.out.println(econo);
        System.out.println("\nViajando com os carros");
        basico.viaja(250);
        basico.viaja(150);
        esportivo.viaja(200);
        utilitario.viaja(200);
        suv.viaja(200);
        suvflex.viaja(200);
        econo.viaja(600);
        System.out.println(basico);
        System.out.println(esportivo);
        System.out.println(utilitario);
        System.out.println(suv);
        System.out.println(suvflex);
        System.out.println(econo);
    }
}
