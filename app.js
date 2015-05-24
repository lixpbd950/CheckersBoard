/**
 * a two-demesion array to store whether a board grid is checked or not
 * initial value '0' means it's not checked;
 * value '1' mean player one has checked this grid;
 * value '2' mean player two has checked this grid;
 */
var checked = new Array(8);

//a boolean to store whether the game has started;
var start = false;

/**
 * a number to store which player will give next move;
 * initial value '0' means game hasn't started and no one needs moving;
 * value'1' means player one needs moving next;
 * value'2' means player two needs moving next;
 */
var turn = 0;

//Create Checker Board by looping rows and columns;
function createBoard() {
	document.write("<table>");
	for (row = 0; row < 8; row++) {
		document.write("<tr>");
        checked[row] = new Array(8);
		for (col = 0; col < 8; col++) {
  		    checked[row][col] = 0;
			if ((row + col) % 2) {
				document.write("<td onclick='makeMove(this)' class='odd'></td>");
			} else {
				document.write("<td onclick='makeMove(this)' class='even'></td>");
			}
		}
		document.write("</tr>");
	}
	document.write("</table>");
}

/**
 * Remove leading and trailing spaces in a string, 
 * which is used to check player's names.
 * This function is quoted from StackOverFlow:
 * http://stackoverflow.com/questions/10032024/how-to-remove-leading-and-trailing-white-spaces-from-a-given-html-string
 */
if(!String.prototype.trim) {  
  String.prototype.trim = function () {  
    return this.replace(/^\s+|\s+$/g,'');  
  };  
} 

//Clear data on the board
function clearBoard(){
    var td = document.getElementsByTagName("td");
    for(var i=0;i<td.length;i++){
        td[i].innerHTML = "";    
    }
    //restore checked array to 0s
    for(var i=0;i<8;i++){
        for(var j=0;j<8;j++){
            checked[i][j] = 0;
        }
    }
}


//show player' names in heading part by acquiring them from input blanks in footing part
function showPlayerName(p1,p2){
    document.getElementById("player1title").innerHTML = p1;
    document.getElementById("player2title").innerHTML = p2;
}


//change player's turn every move
function playerTurn(){
    if(turn!==0){
        var playerOne = document.getElementById("player1").value;
        var playerTwo = document.getElementById("player2").value;
        if(turn===1){
            document.getElementById("nextmove").innerHTML = "Next Move:"+playerOne;
            turn = 2;
        }else{
            document.getElementById("nextmove").innerHTML = "Next Move:"+playerTwo;
            turn = 1;
        }
    }
}

//Start game after putting two differnt player's names, trigered by 'start' button
function startGame(){
    var playerOne = document.getElementById("player1").value.trim();
    var playerTwo = document.getElementById("player2").value.trim();
    if(playerOne==""){
        alert("Please input player one's name.");
    }else if(playerTwo==""){
        alert("Please input player two's name.");
    }else{
        if(playerOne==playerTwo){
            alert("Please input different names.");
        }else{
            clearBoard();
            start=true;
            showPlayerName(playerOne,playerTwo);
            //alert("Start Game!");  
            turn = 1;
            playerTurn(); 
        }
    }
}

//show/hide checkers on board, depending on whether a grid is occupied
function makeMove(e){
    if(turn!==0 && start){
        var row = e.parentNode.rowIndex;
        var col = e.cellIndex;
        //alert(row+" "+col);
        if(checked[row][col]===0){
            checked[row][col] = turn;
            if(turn===1){
                e.innerHTML = "<img src='black.png' widith=60px height=60px>";    
            }else{
                e.innerHTML = "<img src='white.png' widith=60px height=60px>";
            }    
        }else{
            checked[row][col] = 0;
            e.innerHTML = null;
        }
        playerTurn();
    }
}
