

package principal;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author tvlenin
 */
public class enemigo extends JLabel implements Runnable{
    private int posx = 0;
    private int posy = 0;
    private URL url = getClass().getResource("/img/OVNI.png");
    ImageIcon icon = new ImageIcon(url);
    Thread hilo;
    private int mov = 0;
    private int movi = 1;
    private int cont = 0;
    
    public enemigo (int x,int y, int movi){
        this.posx = x;
        this.posy = y;
        setVisible(true);
        setIcon(icon);
        setBounds(posx,posy , icon.getIconWidth(), icon.getIconHeight());// indica las opciones del label como posicion en x, y el ancho y el largo
        setFocusable(true);
        this.movi = movi;
}

    
    //metodo con las diferentes maneras en las que se puede mover el enemigo
    public void mover(int o){

        switch (o){
            // el caso 1 es un movimiento en cuadro 
            case 1 :
                if (cont <= 140){
                    posx += 4;
                    cont += 2;
                    setBounds(posx,posy , icon.getIconWidth(), icon.getIconHeight());
                }else if (cont > 140 && cont <= 280){
                    posy += 4;
                    cont += 2;
                    setBounds(posx,posy , icon.getIconWidth(), icon.getIconHeight());
                }else if (cont > 280 && cont <= 420){
                    posx -= 4;
                    cont += 2;
                    setBounds(posx,posy , icon.getIconWidth(), icon.getIconHeight());
                }else if (cont > 420 && cont <= 560){
                    posy -= 4;
                    cont += 2;
                    setBounds(posx,posy , icon.getIconWidth(), icon.getIconHeight());
                }else
                    cont = 0;


                break;
                //caso 2 es un movimiento de arriba-abajo con una pequena inclinacion hacia x
             case 2 :
                if (posy >= 40  && mov == 0){
                    posx += 1;
                    posy -= 10;
                    setBounds(posx,posy , icon.getIconWidth(), icon.getIconHeight());

                }else if (posy < 440 ){
                    mov = 1;
                    posx -= 1;
                    posy += 10;
                    setBounds(posx,posy , icon.getIconWidth(), icon.getIconHeight());
                    if (posy >= 440)
                        mov =0;
                }
                break;
            default :
                System.out.println("");
        }
    }  
    //metodo para mover los objetos de la pantalla hacia la nave
    public void atras(){
        posx -= 5;
        setBounds(posx,posy , icon.getIconWidth(), icon.getIconHeight());

    }
    //metodo para alejarse de la nave
    public void adelante(){
        posx += 5;
        setBounds(posx,posy , icon.getIconWidth(), icon.getIconHeight());

    }
    public void start(){
        if(hilo==null){
            hilo=new Thread(this);
            hilo.start();
        }
    }
    //metodo para detener el hilo
    public void stop(){
        if(hilo!=null){
            hilo.stop();
            hilo=null;
        }
    }
    // metodo donde se encuentran las instrucciones para repetir en el hilo
    @Override
    public void run() {
        while (true) {
            try{
                // define el tiempo cada cuanto se baja una posicion 
                Thread.sleep(70);
            }catch (InterruptedException e) { }
            // baja la nave una posicion en y cada 30 milisegundos, definidos en el sleep de arriba
            mover(this.movi);
        }

    }   
}
    

