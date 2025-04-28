package datastr;

public class MyBSTNode <Ttype> {
	
	private Ttype element;
	private MyBSTNode<Ttype> leftCh = null;
	private MyBSTNode<Ttype> rightCh = null;
	private MyBSTNode<Ttype> parent = null;


	public Ttype getElement() {
		return element;
	}

	public MyBSTNode<Ttype> getLeftCh() {
		return leftCh;
	}
	
	public MyBSTNode<Ttype> getRightCh() {
		return rightCh;
	}
	
	public MyBSTNode<Ttype> getParent() {
		return parent;
	}
	public void setElement(Ttype inputElement) {
		if(inputElement != null)
		{
			element = inputElement;
		}
		else
		{
			element = (Ttype) new Object();
		}
	}



	public void setLeftCh(MyBSTNode<Ttype> leftCh) {
		this.leftCh = leftCh;
	}

	public void setRightCh(MyBSTNode<Ttype> rightCh) {
		this.rightCh = rightCh;
	}

	public void setParent(MyBSTNode<Ttype> parent) {
		this.parent = parent;
	}

	
	public MyBSTNode(Ttype inputElement) {
		setElement(inputElement);
	}
	
	public String toString()
	{
		return "" + element;
	}
	
	

}