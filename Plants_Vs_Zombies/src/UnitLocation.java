
public class UnitLocation {


    private int row, col;

    /**
     * Constructor for objects of class UnitLocation
     */
    public UnitLocation(int newRow, int newCol)
    {
        row = newRow;
        col = newCol;
    }

    /**
     * Returns the piece's row position
     */
    public int getRow()
    {
        return row;
    }
    /**
     * Returns the piece's column position
     */
     public int getCol()
    {
        return col;
    }
    
    /**
     * Sets the piece's row number to variable r
     */
    public void setRow(int r)
    {
        row = r;
    }
    
    /** 
     * Sets the piece's column number to variable c
     */
    public void setCol (int c)
    {
        col = c;
    }
    
    /**
     * Sets the piece's location to a new UnitLocation
     */
    public void setLocation(int r, int c)
    {
        this.row = r;
        this.col = c;
    }
    
    /**
     * Checks if the passed UnitLocation is the same as this unit's UnitLocation
     */
    public boolean equals(UnitLocation ul)
    {
        if (ul.getRow() == row && ul.getCol() == col)
            return true;
        return false;
    }

}
