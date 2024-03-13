public class Carro {

    private String modelo;
    private Motor motor;
    private TanqueCombustivel tanque;
    private TipoCombustivel tipoAtual;

    public Carro(String modelo, TipoCombustivel tipoCombustivel, int consumoMotor, int capacidadeTanque) {
        this.modelo = modelo;
        motor = new Motor(tipoCombustivel, consumoMotor);
        tanque = new TanqueCombustivel(tipoCombustivel, capacidadeTanque);
    }

    public Carro(String modelo, TipoCombustivel tipoCombustivel, int consumoMotorG, int consumoMotorA, int capacidadeTanque) {
        this.modelo = modelo;
        motor = new Motor(tipoCombustivel, consumoMotorA, consumoMotorG);
        tanque = new TanqueCombustivel(tipoCombustivel, capacidadeTanque);
    }

    public Carro(String modelo, TipoCombustivel tipoCombustivel, int capacidadeTanque, int consumoMotor, int desgaste, int distancia, int maxDesgaste) {
        this.modelo = modelo;
        this.motor = new Motor(TipoCombustivel.GASOLINA, consumoMotor, desgaste, distancia, maxDesgaste); // consumo inicial de 20 Km/lt, reduzindo 1 Km/lt a cada 5000 Km
        this.tanque = new TanqueCombustivel(TipoCombustivel.GASOLINA, capacidadeTanque);
        this.tipoAtual = TipoCombustivel.GASOLINA;
    }

    public String getModelo() {
        return modelo;
    }

    public int getCombustivelDisponivel() {
        return tanque.getCombustivelDisponivel();
    }

    // Retorna a quantidade efetivamente abastecida
    public int abastece(TipoCombustivel tipoCombustivel, int quantidade) {
        int capacidadeLivre = tanque.getCapacidade() - tanque.getCombustivelDisponivel();
        if (capacidadeLivre < quantidade) {
            tanque.abastece(tipoCombustivel, capacidadeLivre);
            this.tipoAtual = tipoCombustivel;
            return capacidadeLivre;
        } else {
            tanque.abastece(tipoCombustivel, quantidade);
            this.tipoAtual = tipoCombustivel;
            return quantidade;
        }
    }

    // Retorna a distancia que consegue viajar com o combustivel remanescente
    public int verificaSePodeViajar(int distancia) {
        int combustivelNecessario = motor.combustivelNecessario(distancia);
        if(motor.getTipoMotor() == TipoCombustivel.FLEX){
            combustivelNecessario = motor.combustivelNecessarioFlex(distancia, this.tipoAtual);
        }
        if (tanque.getCombustivelDisponivel() >= combustivelNecessario) {
            return distancia;
        } else {
            return tanque.getCombustivelDisponivel() * motor.getConsumo();
        }
    }

    // Retorna true se conseguiu viajar
    public boolean viaja(int distancia) {
        if (verificaSePodeViajar(distancia) >= distancia) {
            if (motor.getDesgaste() != 0) {
                int distanciaTotal = distancia;
                int distanciaPorPercorrer;
                while (distanciaTotal > 0) {
                    // Determine the distance to travel in this iteration
                    if (distanciaTotal >= motor.getDistancia()) {
                        distanciaPorPercorrer = motor.getDistancia();
                    } else {
                        distanciaPorPercorrer = distanciaTotal;
                    }
                    motor.percorre(distanciaPorPercorrer);
                    if (motor.getTipoMotor() == TipoCombustivel.FLEX) {
                        tanque.gasta(motor.combustivelNecessarioFlex(distanciaPorPercorrer, this.tipoAtual));
                    } else {
                        tanque.gasta(motor.combustivelNecessario(distanciaPorPercorrer));
                    }
                    distanciaTotal -= distanciaPorPercorrer;
                }
            }
            else{
                motor.percorre(distancia);
                if(motor.getTipoMotor() == TipoCombustivel.FLEX){
                    tanque.gasta(motor.combustivelNecessarioFlex(distancia, this.tipoAtual));
                }
                else{
                    tanque.gasta(motor.combustivelNecessario(distancia));
                }
                return true;
            }

        }
        return false;
    }

    @Override
    public String toString() {
        return "Carro:\n  Modelo=" + modelo + "\n  Motor=" + motor + "\n  Tanque=" + tanque;
    }
}
