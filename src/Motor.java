public class Motor {

    private TipoCombustivel tipoMotor;
    private int consumo;
    private int consumoA; // em quilometros por unidade. Ex: Km/Lt
    private int quilometragem;
    private int desgaste;
    private int distancia;
    private int maxDesgaste;

    {
        desgaste = 0; // Valor padrão para desgaste
        distancia = 1; // Valor padrão para distância
        maxDesgaste = 0; // Valor padrão para máximo desgaste
    }


    public Motor(TipoCombustivel tipoMotor, int consumo) {
        this.tipoMotor = tipoMotor;
        this.consumo = consumo;
    }

    public Motor(TipoCombustivel tipoMotor, int consumo, int consumoA) {
        this.tipoMotor = tipoMotor;
        this.consumo = consumo;
        this.consumoA = consumoA;
    }

    public Motor(TipoCombustivel tipoMotor, int consumo, int desgaste, int distancia, int maxDesgaste) {
        this.tipoMotor = tipoMotor;
        this.consumo = consumo;
        this.desgaste = desgaste;
        this.distancia = distancia;
        this.maxDesgaste = maxDesgaste;
    }

    public int getDesgaste() {
        return this.desgaste;
    }

    public int getDistancia() {
        return this.distancia;
    }

    public int maxDesgaste() {
        return this.maxDesgaste;
    }

    public int getConsumo() {
        return this.consumo;
    }

    public int getConsumoA() {
        return this.consumoA;
    }

    public TipoCombustivel getTipoMotor(){
        return this.tipoMotor;
    }

    public int getQuilometragem(){
        return this.quilometragem;
    }

    public int combustivelNecessario(int distancia) {
        return distancia / consumo;
    }

    public int combustivelNecessarioFlex(int distancia, TipoCombustivel tipoAtual) {
        if(tipoAtual == TipoCombustivel.GASOLINA){
            return distancia / consumo;
        }
        return distancia / consumoA;
    }

    public void percorre(int distancia) {
        int sobraAnterior = quilometragem%this.distancia;
        quilometragem += distancia;
        consumo = Math.max(consumo - desgaste*(sobraAnterior+distancia)/this.distancia, maxDesgaste);
        System.out.println(consumo);
    }

    @Override
    public String toString() {
        if(tipoMotor == TipoCombustivel.FLEX){
            return "Motor [consumo gasolina=" + consumo + ", consumo alcool=" + consumoA + ", quilometragem=" + quilometragem + ", tipoMotor=" + tipoMotor + "]";
        }
        return "Motor [consumo=" + consumo + ", quilometragem=" + quilometragem + ", tipoMotor=" + tipoMotor + "]";
    }
}