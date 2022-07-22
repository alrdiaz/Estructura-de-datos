//Estas clases fueron desarrolladas como research al ejercicio y de manera academica 
//pero no se usaron para el funcionamiento del api rest, en su lugar se utilizo
//la calse de java treeMap que es en si una estructura de arbol binario 
//balanceado red-black.


package com.commons;

public class SearchBinaryTree<T> {
	SearchBinaryTreeNode<T> root;
//--------------------------------------------------------------
	public boolean isEmpty() {
		if (root == null) {
			return true;
		}
		return false;
	}
//--------------------------------------------------------------	
	public void Visit(SearchBinaryTreeNode<T> root){
        if(root==null){
            System.out.println("this tree is empty!");
        }
        System.out.println(root.getData());
    }
//--------------------------------------------------------------	
	public SearchBinaryTreeNode<T> getRoot(){
        if(root==null){
            root=new SearchBinaryTreeNode<T>();
        }
        return root;
    }
//--------------------------------------------------------------
	public SearchBinaryTree(){
        this.root=null;
    }
//--------------------------------------------------------------
	public void CreateTree(SearchBinaryTreeNode<T> node, T data) {
        if (root == null) {
            root = new SearchBinaryTreeNode<T>();
        } else {
            if (Math.random ()> 0.5) {// Crea un árbol binario de forma aleatoria
                if (node.leftChild == null) {
                    node.leftChild = new SearchBinaryTreeNode<T>(data);
                } else {
                    CreateTree(node.leftChild, data);
                }
            } else {
                if (node.rightChild == null) {
                    node.rightChild = new SearchBinaryTreeNode<T>(data);
                } else {
                    CreateTree(node.rightChild, data);
                }
            }
        }
    }
//--------------------------------------------------------------
	//metodo recursivo de busqueda
	public SearchBinaryTreeNode<T> search(SearchBinaryTreeNode<T> root,T value){
        SearchBinaryTreeNode<T> current=root;
        while((root!=null)&&(current.getData()!=value)){
// Cabe señalar que los tipos genéricos en Java no se 
//pueden comparar en tamaño. En el uso real, se procede a comparar con compareTo()
//a las variables resultantes del metodo toString() del SearchBinaryTreeNode  y casteo String de value   	
        	String stringcomparevalue = (String) value;       	
            if (stringcomparevalue.compareTo(current.toString())<0) {
				current = search(current.leftChild,value);
            }
			else if (stringcomparevalue.compareTo(current.toString())>0) {
				current = search(current.rightChild,value);
		    }		
        }
        return current;
    }
//--------------------------------------------------------------
	public SearchBinaryTreeNode<T> insertNode( T value){
        SearchBinaryTreeNode<T> p=root,pre=null;
        String stringcomparevalue = (String) value;
        //busqueda de posicion del nodo hasta que p =null 
        //para definir padre=pre y luego en if-elseif 
        //crear nodo de arbol a partir de value de cualquier type T
        while(p!=null){
            pre=p;
// Cabe señalar que los tipos genéricos en Java no se 
//pueden comparar en tamaño. En el uso real, se procede a comparar con compareTo()
//a las variables resultantes del metodo toString() del SearchBinaryTreeNode y casteo String de value                    
            if(stringcomparevalue.compareTo(p.toString())>0){
                p=p.rightChild;
            }else if (stringcomparevalue.compareTo(p.toString())<0){
                p=p.leftChild;
            }
        }        
        if(root==null){
            root=new SearchBinaryTreeNode<T>(value);
        }else if(stringcomparevalue.compareTo(pre.toString())>0){            
        	pre.rightChild=new SearchBinaryTreeNode<T>(value);
        }else if (stringcomparevalue.compareTo(pre.toString())<0){
            pre.leftChild=new SearchBinaryTreeNode<T>(value);
        }
		return null;
    }
//--------------------------------------------------------------	
	public void deleteByMerging(SearchBinaryTreeNode<T> node){
        SearchBinaryTreeNode<T> temp=node;
        if(node!=null){
                         // Si el nodo eliminado no tiene un subárbol derecho, use el nodo raíz de su subárbol izquierdo para reemplazar el nodo eliminado
            if(node.rightChild!=null){
                node=node.leftChild;
            }else if(node.leftChild==null){
                                 // Si el nodo eliminado no tiene un subárbol izquierdo, reemplace el nodo eliminado con el nodo más a la izquierda con el recuento de palabras
                node=node.rightChild;
            }else{
                                 // Si existen los subárboles izquierdo y derecho del nodo eliminado
                temp=node.leftChild;
                while(temp.rightChild!=null){
                                         temp = temp.rightChild; // Encuentra el nodo derecho del subárbol izquierdo
                }
                
                                 // Asignar el puntero derecho del nodo encontrado a la raíz del subárbol derecho del nodo eliminado
                temp.rightChild=node.rightChild;
                temp=node;
                node=node.leftChild;
            }
            temp=null;
        }
    }
//--------------------------------------------------------------
	public void deleteByCoping(SearchBinaryTreeNode<T> node){
        SearchBinaryTreeNode<T> pre=null;
        SearchBinaryTreeNode<T> temp=node;
                 // Si el nodo eliminado no tiene un subárbol derecho, reemplace el nodo eliminado con el nodo raíz de su subárbol izquierdo
        if(node.rightChild==null){
            node=node.leftChild;
        }else if(node.leftChild==null){
            node=node.rightChild;
        }else{
                         // Si existen los subárboles izquierdo y derecho del nodo eliminado
            temp=node.leftChild;
            pre=node;
            while(temp.rightChild!=null){
                pre=temp;
                                 temp = temp.rightChild; // Recorra para encontrar el nodo más a la derecha del subárbol izquierdo
            }
                         node.data = temp.data; // Realizar operación de asignación
            if(pre==node){
                pre.leftChild=node.leftChild;
            }else{
                pre.rightChild=node.rightChild;
            }
        }
        temp=null;
    }
//--------------------------------------------------------------
	// EMPIEZA EL RECORRIDO EN PREORDEN
    public synchronized void traversePreOrder()
    {
        auxPreorder(root);
    }
    //metodo recursivo para recorrido en preorden
    
    private void auxPreorder(SearchBinaryTreeNode<T> nodo)
    {
        if(nodo == null)
            return;
        
        System.out.print(nodo.toString() + " ");     //mostrar datos del nodo        
        auxPreorder(nodo.leftChild);   //recorre subarbol izquierdo
        auxPreorder(nodo.rightChild);     //recorre subarbol derecho
    }	
	
}
