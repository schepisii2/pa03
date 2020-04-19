import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Node//construct node
{
	String value;
	Node next;

	public Node(String value)
	{
		this.value=value;
		this.next=null;
	}
}

class Queue
{
	Node front, rear;
	
	public Queue()//construct queue
	{
		this.front=this.rear=null;
	}

	void enqueue(String value)
	{
		Node temp = new Node(value);

		if (this.rear==null)//if empty, add node as front/rear
			this.front=this.rear=temp;
		else//enqueue to list
		{
			this.rear.next=temp;
			this.rear=temp;
		}
		return;
	}

	String dequeue()
	{
		if (this.front==null)//check for null
			return null;
		//else
		Node temp = this.front;
		this.front=this.front.next;
		if (this.front == null)
			this.rear=null;
		return temp.value;
	}
}

public class sortit {

	public static void main(String[] args)
	{
		/* test queue
		Queue q = new Queue();
		q.enqueue("3");
		q.enqueue("2");
		q.enqueue("1");
		System.out.println("Queue Front: " +q.front.value);
		System.out.println("Queue Rear: " +q.rear.value);
		System.out.println(q.dequeue() + q.dequeue() + q.dequeue());
		if (q.dequeue()==null)
			System.out.println("nice");*/

		for (String s: args)
		{
			if (s!="-"){
				try {
					File myFile = new File(s);
					Scanner myScanner = new Scanner(myFile);
					while (myScanner.hasNextLine())
					{
						String line = myScanner.nextLine();
						System.out.println(line);
					}
					myScanner.close();
				} catch (FileNotFoundException e) {
					System.out.println("Error: could not find input file");
					System.exit(1);
				}
			}
			else//s==-
			{
				Scanner input = new Scanner(System.in);
				System.out.println("Enter object: ");
				String myObject=input.nextLine();
				System.out.println(myObject);

			}
		}
	}
}
