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
	public static void addToList(String object, Queue list0, Queue list1, Queue curlist)
	{
			if ((curlist.rear==null)||(curlist.rear.value==null))//the list is empty
			{
				curlist.enqueue(object);
				return;
			}
			if (curlist.rear.value.compareTo(object)>0)
			{
				if (curlist==list0)
					curlist=list1;
				else
					curlist=list0;
				curlist.enqueue(object);
				return;
			}
			curlist.enqueue(object);
			return;

	}

	public static void main(String[] args)
	{
		Queue list0 = new Queue();
		Queue list1 = new Queue();
		Queue curlist = list0;
		String temp;

		for (String s: args)
		{
			if (s.compareTo("-")!=0){//file input
				try {
					File myFile = new File(s);
					Scanner myScanner = new Scanner(myFile);
					while (myScanner.hasNextLine())
					{
						temp = myScanner.nextLine();
						addToList(temp, list0, list1, curlist);
					}
					myScanner.close();
				} catch (FileNotFoundException e) {
					System.out.println("Error: could not find input file");
					System.exit(1);
				}
			}
			else//standard input
			{
				Scanner input = new Scanner(System.in);
				do{
					System.out.print("Enter object: ");
					temp=input.nextLine();
					addToList(temp, list0, list1, curlist);
				}while(temp.length()!=0);
				input.close();
			}
		}

		while ((list0.front!=null)&&(list1.front!=null))
		{
			//dummy value to check if list is exhausted
			list0.enqueue(null);
			list1.enqueue(null);

			while((list0.front.value!=null)&&(list1.front.value!=null))
			{
				if(list0.front.value.compareTo(list1.front.value)>0)
				{
					temp = list1.dequeue();
					addToList(temp, list0, list1, curlist);
				}
				else
				{
					temp = list0.dequeue();
					addToList(temp, list0, list1, curlist);
				}
			}

			if (list0.front.value==null)//list0 exhausted
			{
				list0.dequeue();
				while(list1.front.value!=null)
				{
					temp = list1.dequeue();
					addToList(temp, list0, list1, curlist);
				}
				list1.dequeue();
			}
			else //list1 exhausted
			{
				list1.dequeue();
				while(list0.front.value!=null)
				{
					temp = list0.dequeue();
					addToList(temp, list0, list1, curlist);
				}
				list0.dequeue();
			}
		}
		
		while(list0.front!=null)
		{
			System.out.println(list0.dequeue());
		}
		while(list1.front!=null)
		{
			System.out.println(list1.dequeue());
		}
	}
}
