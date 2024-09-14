package application;

public class NodeYear {
	NodeYear next;
	private SingleLinkListMonth element;

	NodeYear(SingleLinkListMonth element) {
		this.element = element;
	}

	public int compareTo(SingleLinkListMonth obj) {
		return this.element.date.compareTo(obj.date);
	}

	public boolean equals(SingleLinkListMonth obj) {
		return this.element.date.equals(obj.date);
	}

	public NodeYear getNext() {
		return next;
	}

	public void setNext(NodeYear next) {
		this.next = next;
	}

	@Override
	public boolean equals(Object obj) {
		if (element.equals(obj))
			return true;
		return false;
	}

	public SingleLinkListMonth getElement() {
		return element;
	}

	public void setElement(SingleLinkListMonth element) {
		this.element = element;
	}

	@Override
	public String toString() {
		return "Node [next=" + next + ", element=" + element + "]";
	}
}
