/*
 * Author: Shuai Zheng
 * Edited By: Josue Galeas
 * Last Edit: May 5, 2016
 * Description: Class for graphing that data points.
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class TransformScale extends JComponent
{
	Shape axes, shape;
	int length = 400, arrowLength = 7, tickSize = 4;
	List<Coordinate<Double>> input;
	int originalNodes;

	public TransformScale()
	{
		axes = createAxes();
		shape = createShape();
	}

	protected Shape createAxes()
	{
		GeneralPath path = new GeneralPath();

		// Axes.
		path.moveTo(-length, 0);
		path.lineTo(length, 0);
		path.moveTo(0, -length);
		path.lineTo(0, length);
		// Arrows.
		path.moveTo(length - arrowLength, -arrowLength);
		path.lineTo(length, 0);
		path.lineTo(length - arrowLength, arrowLength);
		path.moveTo(-arrowLength, length - arrowLength);
		path.lineTo(0, length);
		path.lineTo(arrowLength, length - arrowLength);
		// Half-centimeter tick marks
		float cm = 72 / 2.54f;
		float lengthCentimeter = length / cm;
		for (float i = 0.5f; i < lengthCentimeter; i += 1.0f)
		{
			float tick = i * cm;
			path.moveTo(tick, -tickSize / 2);
			path.lineTo(tick, tickSize / 2);
			path.moveTo(-tick, -tickSize / 2);
			path.lineTo(-tick, tickSize / 2);
			path.moveTo(-tickSize / 2, tick);
			path.lineTo(tickSize / 2, tick);
			path.moveTo(-tickSize / 2, -tick);
			path.lineTo(tickSize / 2, -tick);
		}
		// Full-centimeter tick marks
		for (float i = 1.0f; i < lengthCentimeter; i += 1.0f)
		{
			float tick = i * cm;
			path.moveTo(tick, -tickSize);
			path.lineTo(tick, tickSize);
			path.moveTo(-tick, -tickSize);
			path.lineTo(-tick, tickSize);
			path.moveTo(-tickSize, tick);
			path.lineTo(tickSize, tick);
			path.moveTo(-tickSize, -tick);
			path.lineTo(tickSize, -tick);
		}

		return path;
	}

	protected Shape createShape()
	{
		float cm = 72 / 2.54f;
		return new Rectangle2D.Float(cm, cm, 2 * cm, cm);
	}

	public void paint(Graphics g)
	{
		try
		{
			Graphics2D g2 = (Graphics2D) g;

			// Use antialiasing.
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			// Move the origin
			AffineTransform at = AffineTransform.getTranslateInstance(10, 10);
			g2.transform(at);

			// Draw the shapes in their original locations.
			g2.setPaint(Color.black);
			g2.draw(axes);

			Graphics2D g2D;
			g2D = (Graphics2D) g;
			g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			// float X = null, Y = null, datastring = null;

			double X ,Y;
			int X1,Y1;

			for (int i = 0; i < GlobalConstants.n; i++)
			{
				String fileName = "./bin/circle.png";
				Image img = getToolkit().getImage(fileName);
				AffineTransform aTran = new AffineTransform();
				X = input.get(i).getX();
				Y = input.get(i).getY();
				X1 = (int)X/100;
				Y1 = (int)Y/100;

				g2D.drawImage(img,X1,Y1, this);

			}
			int entries = input.size();

			for (int i = 10; i < entries; i++)
			{
				String fileName2 = "./bin/X.png";
				Image img2 = getToolkit().getImage(fileName2);
				AffineTransform aTran = new AffineTransform();
				X = input.get(i).getX();
				Y = input.get(i).getY();
				X1 = (int)X/100;
				Y1 = (int)Y/100;

				g2D.drawImage(img2,X1,Y1, this);
			}
		}
		catch (Exception x)
		{
			// TODO: Error handling.
		}
	}

	public void getList(List<Coordinate<Double>> input)
	{
		this.input = input;
	}

	public static void main(String[] args)
	{
		JFrame f = new JFrame();
		f.getContentPane().add(new TransformScale());
		f.getContentPane().setBackground(Color.WHITE);
		f.setSize(700, 600);
		f.setVisible(true);
	}
}
