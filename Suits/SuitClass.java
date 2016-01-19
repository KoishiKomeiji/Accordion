//SuitClass.java by Howard Zeng 2011.12.10
package Suits;

import java.awt.*;
import hsa.Console;
import Shapes.*;


public abstract class SuitClass extends ShapeClass
{

    public void setWidth (int n)
    {
	iWidth = (iHeight * 4) / 5;
    }


    public void setHeight (int n)
    {
	iHeight = n;
	iWidth = (iHeight * 4) / 5;
    }
}
