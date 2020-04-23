<html>
<head>
<link href="../../style/popUpWindow.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="../../js/common/popWindowScript.js"></script>
</head>
<body>
    <div id="toPopup" style="overflow: auto"> 
    	
        <div class="close"></div>
       	<span class="ecs_tooltip">Press Esc to close <span class="arrow"></span></span>
		<div id="popup_content"> <!--your content start-->
           <!--  <p>
            MACHINE INFO </p>
            <br />
             <p align="left">
           NEW ARRIVALS</p>
             <p align="left">
            COMMENTS </p>
            <br /> -->
             <p align="left">
            ADD COMMENTS 
           </p>
            <textarea rows="5" cols="75" id="comments" placeholder="Add machine servicing information/comments" ></textarea>
           <p><button type="button" onclick="updateComments();">Update Comments</button><div id="deleteMachine" style="display: none"><button type="button" onclick="alert('under development');">Delete Machine</button></div>
           </p>
            <p align="center"><a href="#" class="livebox">Click Here to see machine maintenance history</a></p>
        </div> <!--your content end-->
    
    </div> <!--toPopup end-->
    
	<div class="loader"></div>
   	<div id="backgroundPopup"></div>
</body>
</html>