Este archivo tiene como intencion recordarme como utilizar las clases y
en caso de que alguien mas lo lea demostrarle como se utilizan.

Para utilizar las clases basicas, ya sean Pixel o cualquiera de las
incluidas en los paquetes Lines, Figures y Filling es necesario seguir
el siguiente procedimento:

Extender de la clase JFrame o MyJFrame, crear un JPanel, un BufferedImage,
un Graphics e incluir cuantas clases sean necesarias de los paquetes
anteriormente mencionados, ejemplo:
	
	public class BoundaryFillTest extends MyJFrame {
    	private JPanel panel;
    	private BufferedImage buffImage;
	private AbstractLine line;

Luego al llamar al constructor es necesario inicializarlas de esta
manera o de forma similar

	panel = new JPanel();
        add(panel);
        this.setVisible(true);  //is set visible again because a new element is added

        buffImage = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g2 = buffImage.createGraphics();
        g2.setColor(panel.getBackground());
        g2.fillRect(0, 0, panel.getWidth(), panel.getHeight());

Notece que al utilizar cualquiera de las clases de los paquetes se deben
incluir ciertos parametros que varian de una clase a otra, pero la mayoria
requiere el Contenedor y los Graficos.

        boundaryFill = new BoundaryFill(panel, buffImage, g2);
        rect = new Figures.Rectangle(panel, g2);
        line = new BresenhamLine(panel, g2);

Finalmente, una vez dibujado todo lo que se requiere, se dibuja la imagen
de la siguiente manera:

	panel.getGraphics().drawImage(buffImage, 0, 0, panel);

Todo se dibuja en un buffer por cuestiones de rendimiento por ejemplo al
utilizar rellenos, pueden agregarse (separar secciones de dibujo) tantos
buffers como sean necesarios.