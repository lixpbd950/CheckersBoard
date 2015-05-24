package com.krowdbyz.checkersboard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.hp.gagawa.java.Document;
import com.hp.gagawa.java.DocumentType;
import com.hp.gagawa.java.elements.Br;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Input;
import com.hp.gagawa.java.elements.Link;
import com.hp.gagawa.java.elements.Script;
import com.hp.gagawa.java.elements.Text;
import com.hp.gagawa.java.elements.Title;

public class CheckersBoard {
	//create head in html
	public static Document creatHead(Document document){
		//Append title to head
		Title title = new Title();
		Text titleText = new Text("KrowdByz Checkers Board - By Xiaoping Li");
		title.appendChild(titleText);
		document.head.appendChild(title);
		
		//Append css to head
		Link link = new Link();
		link.setRel("stylesheet");
		link.setType("text/css");
		link.setHref("style.css");
		document.head.appendChild(link);
		
		//Append js file to head
		Script script = new Script("text/javascript");
		script.setSrc("app.js");
		document.head.appendChild(script);
		
		return document;
	}
	//create body in html
	public static Document createBody(Document document){
		
		//creat container for whole elements in body
		Div container = new Div();
		container.setAttribute("class", "container");
		
		//creating heading in container
		Div heading = new Div();
		heading.setAttribute("class", "heading");
		
		//create two players' title and next move
		Div player1title = new Div();
		player1title.setId("player1title").appendText("player1");
		heading.appendChild(player1title);
		
		Div nextmove = new Div();
		nextmove.setId("nextmove").appendText("next move");
		heading.appendChild(nextmove);
		
		Div player2title = new Div();
		player2title.setId("player2title").appendText("player2");
		heading.appendChild(player2title);
		
		//append heading to container
		container.appendChild(heading);
		
		
		//create a board in container
		Div board = new Div();
		board.setAttribute("class", "board");
		Script script2 = new Script(null);
		script2.appendText("createBoard()");
		board.appendChild(script2);
		
		//append board to container
		container.appendChild(board);
		
		//create footing in container
		Div footing = new Div();
		footing.setAttribute("class", "footing");
		
		//create two players' input and a 'start' button
		Div players = new Div();
		players.setId("players");
		
		Input input1 = new Input();
		input1.setType("text").setId("player1").setAttribute("placeholder", "Input Player One");
		
		Input input2 = new Input();
		input2.setType("text").setId("player2").setAttribute("placeholder", "Input Player Two");
		
		players.appendChild(new Text("Player1:")).appendChild(input1).appendChild(new Br());
		players.appendChild(new Text("Player2:")).appendChild(input2).appendChild(new Br());
		
		//append players' input area to footing
		footing.appendChild(players);
		
		Input button = new Input();
		button.setType("button").setId("btn").setValue("Start!").setAttribute("onclick", "startGame()");
		
		//append start button to footing
		footing.appendChild(button);
		
		//append footing to container
		container.appendChild(footing);
		
		//append container to body
		document.body.appendChild(container);
		
		return document;
	}
	
	//output html file in current folder
	public static void outputFile(Document document){
		
		//create a new file
		File file = new File("./CheckersBoard.html");
		try {
			FileOutputStream fos = new FileOutputStream(file);
			if(!file.exists()){
				file.createNewFile();
			}
			//convert document strings to byte array
			byte[] bytes = document.write().getBytes();
			fos.write(bytes);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Document document = new Document(DocumentType.HTMLTransitional);
		document = creatHead(document);
		document = createBody(document);
		outputFile(document);
	}

}
