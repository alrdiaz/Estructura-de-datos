//Estas clases fueron desarrolladas como research al ejercicio y de manera academica 
//pero no se usaron para el funcionamiento del api rest, en su lugar se utilizo
//la calse de java treeMap que es en si una estructura de arbol binario 
//balanceado red-black.

package com.commons;


//https://programmerclick.com/article/11901388485/

public class SearchBinaryTreeNode<T> {
	T data;
	public SearchBinaryTreeNode<T> leftChild;
	public SearchBinaryTreeNode<T> rightChild;

	public SearchBinaryTreeNode() {
	this.data = null;
	this.leftChild = this.rightChild = null;
	}
	
	public SearchBinaryTreeNode(T da){
    this.data=da;
    this.leftChild=this.rightChild=null;
    }
	
	public SearchBinaryTreeNode(T da,
			                    SearchBinaryTreeNode<T> left,
			                    SearchBinaryTreeNode<T>right)
	{
    this.data=da;
    this.leftChild=left;
    this.rightChild=right;
    }

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public SearchBinaryTreeNode<T> getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(SearchBinaryTreeNode<T> leftChild) {
		this.leftChild = leftChild;
	}

	public SearchBinaryTreeNode<T> getRightChild() {
		return rightChild;
	}

	public void setRightChild(SearchBinaryTreeNode<T> rightChild) {
		this.rightChild = rightChild;
	}
	
	public boolean isLeaf(){
        if(this.leftChild==null&&this.rightChild==null){
            return true;
        }
		return false;
    }

	@Override
	public String toString() {
		return  (String) data;
	}
	
	
	


}
