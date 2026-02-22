import excepciones.IdRepetidoException;
import excepciones.TiempoInvalidoException;
import excepciones.TipoInvalidoException;

public class CentroAtencion {
    int[] ids;
    char[] tipos;
    int[] tiempos;
    int n;


    public CentroAtencion() {
        ids = new int[50];
        tipos = new char[50];
        tiempos = new int[50];
        n = 0;
    }
    public void registrarTurno(int id, char tipo, int tiempoEstimado) throws TiempoInvalidoException, TipoInvalidoException, IdRepetidoException {
        if((tipo!='G'&&tipo!='P')&&(tipo!='g'&&tipo!='p')) throw new TipoInvalidoException("Tipo Invalido, este argumento solo puede guardar p y g");
        if(tiempoEstimado<1||tiempoEstimado>60) throw new TiempoInvalidoException("el tiempoEstimado solo puede estar entre 1 y 60");
        for(int i : ids ) if(i == id) throw  new IdRepetidoException("este id ya esta registrado");
        ids[n] = id;
        tiempos[n] = tiempoEstimado;
        tipos[n] = tipo;
        n++;
    }
    public void atenderSiguiente(){
        int pos = -1;
        for(int i = 0; i < tipos.length; i++){
            if(tipos[i] == 'p'||tipos[i] == 'P'){
                tipos[i] = '0';
                ids[i] = 0;
                tiempos[i] = 0;
                organizar(ids);
                organizar(tiempos);
                organizar(tipos);
                n--;
                return;
            }
        }
        for(int i = 0; i < tipos.length; i++){
            for (char tipo : tipos){
                if(tipo == 'p'||tipo == 'P'){
                    tipos[i] = '0';
                    ids[i] = 0;
                    tiempos[i] = 0;
                    organizar(ids);
                    organizar(tiempos);
                    organizar(tipos);
                    n--;
                    return;
                }
            }
        }
    }
    private void organizar(int[] v){
            int index = 0;
            for (int i = 0; i < v.length; i++) {
                if (v[i] != 0) {
                    v[index++] = v[i];
                }
            }
            while (index < v.length) {
                v[index++] = 0;

        }
    }
    private void organizar(char[] v){
        int index = 0;
        for (int i = 0; i < v.length; i++) {
            if (v[i] != '0') {
                v[index++] = v[i];
            }
        }
        while (index < v.length) {
            v[index++] = '0';

        }
    }
    public void cancelarTurnoPorId(int id){
        for(int i = 0; i < ids.length; i++){
            if(ids[i] == id){
                ids[i] = 0;
                tiempos[i] = 0;
                tipos[i] = '0';
                organizar(ids);
                organizar(tiempos);
                organizar(tipos);
                n--;
                return;
            }
        }
        System.out.println("id no encontrado");
    }
    public void insertarTurnoUrgente(int id, char tipo, int tiempoEstimado,int pos){
        if(pos>=0&&pos<n) {
            registrarTurno(id, tipo, tiempoEstimado);
            desplazarPosicion(pos, ids);
            desplazarPosicion(pos, tiempos);
            desplazarPosicion(pos, tipos);
        }else System.out.println("no se puede insertar el turno en esta posicion");
    }
    public void desplazarPosicion(int k, int[] v){
        int piedra=0;
        for(int i = n-1; i > k; i--){
            piedra = v[i-1];
            v[i-1] = v[i];
            v[i] = piedra;
        }
    }
    public void desplazarPosicion(int k, char[] v){
        char piedra=0;
        for(int i = n-1; i > k; i--){
            piedra = v[i-1];
            v[i-1] = v[i];
            v[i] = piedra;
        }
    }
    public void estadisticas(){
        int tiempoEstimado = 0;
        for(int tiempo : tiempos)tiempoEstimado+=tiempo;
        System.out.println("tiempoEstimado: "+tiempoEstimado);
        int p=0;
        for(char tipo : tipos){
            if(tipo == 'p'||tipo == 'P')p++;
        }
        System.out.println("el porcentaje de preferenciales es: "+((p*100)/n)+"% y el porcentaje de generales es: "+(100-((p*100)/n))+"%");
        int tiempoMax=0;
        int pos = -1;
        for(int i=0; i<tiempos.length; i++){
            if(tiempos[i]>tiempoMax) {
                tiempoMax = tiempos[i];
                pos = i;
            }
        }
        if(pos!=-1){
            System.out.println("el turno con mayor tiempo dura: "+tiempoMax+" y su id y tipo son: "+ ids[pos]+" "+tipos[pos]);
        }
        int contador=0;
        for(int tiempo : tiempos)if(tiempo%5==0)contador++;
        System.out.println("hay "+contador+" turnos con un tiempo multiplo de 5");
    }
    public void mostrarCola(){
        for(int i=0; i<n; i++){
            System.out.print("cliente numero: "+i+" con numero de id: "+ids[i]);
            System.out.println("de tipo: "+tipos[i]);
            System.out.println("con una duracion de: "+tiempos[i]+" minutos");
        }
    }
}
