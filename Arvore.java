package AED1.Exercicios.TrabalhoFractal2;

import javax.swing.*;
import java.awt.*;

public class Arvore extends JFrame {

    public Arvore() {
        initGUI();
    }

    private void initGUI() {
        setTitle(" Tree Branch Fractal");
        setSize(750, 650); //tamanho x e y em pixels
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.black);
        Arvore.DesenhoCanvas desenho = new Arvore.DesenhoCanvas();
        add(desenho);
        setVisible(true); //mostrar a janela
    }

    static class DesenhoCanvas extends Canvas {
        MinhaLista<RamoDaArvore> lista = new MinhaLista<>();

        @Override
        public void paint(Graphics g) {
            super.paint(g);
//            double altura = getHeight() - getHeight()/10;
            double altura = getHeight();
            double largura = getWidth() / 2;
            double angulo = -90;
            int nivel = 13;

            criaArvoreRecu(nivel, largura, altura, angulo, altura / 2.5);
            for (int i = 0; i < nivel; i++) {
                try {
                    Thread.sleep(115);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (long j = 0; j < lista.getTamanho(); j++) {
                    RamoDaArvore rm = lista.get(j);
                    if (rm.nivel == (nivel - i)) {

                        Graphics2D g2d = (Graphics2D) g;
                        g2d.setStroke(new BasicStroke((int) (getWidth() / 50 / Math.pow(2, nivel - rm.nivel))));

                        g.setColor(new Color(150 * (rm.nivel + 1) / (nivel + 1), 75 + 180 * (nivel - rm.nivel + 1) / (nivel + 1), 0));
                        g.drawLine(rm.x1, rm.y1, rm.x2, rm.y2);
                    }
                }
            }
        }

        private void criaArvoreRecu(int nivel, double x1, double y1, double angulo, double h) {

            if (nivel < 0) {
                return;
            }
            double x2 = x1 + (int) (Math.cos(Math.toRadians(angulo)) * h);
            double y2 = y1 + (int) (Math.sin(Math.toRadians(angulo)) * h);

            lista.insereNoFinal(new RamoDaArvore((int) x1, (int) x2, (int) y1, (int) y2, nivel));

            criaArvoreRecu(nivel - 1, x2, y2, angulo + 25, h / 1.67);
            criaArvoreRecu(nivel - 1, x2, y2, angulo - 35, h / 1.67);

        }
    }

    public static void main(String[] args) {
        Arvore f = new Arvore();
    }
}

