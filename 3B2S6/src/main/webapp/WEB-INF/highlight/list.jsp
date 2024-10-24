<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script src="https://apis.google.com/js/api.js"></script>

<!-- <script src="https://apis.google.com/js/api.js"></script> -->
<script>
function authenticate() {
    return gapi.auth2.getAuthInstance()
        .signIn({scope: "https://www.googleapis.com/auth/youtube.readonly"})
        .then(function() { console.log("Sign-in successful"); },
              function(err) { console.error("Error signing in", err); });
  }
  function loadClient() {
    gapi.client.setApiKey("AIzaSyBIGT4K45TpQVfaN7Uta1gxYfRdWdbtFD0");
    return gapi.client.load("https://www.googleapis.com/discovery/v1/apis/youtube/v3/rest")
        .then(function() { console.log("GAPI client loaded for API"); },
              function(err) { console.error("Error loading GAPI client for API", err); });
  }
  // Make sure the client is loaded and sign-in is complete before calling this method.
  function execute() {
    return gapi.client.youtube.playlistItems.list({
      "part": [
        "snippet"
      ],
      "maxResults": 10,
      "pageToken": "",
      "playlistId": "PLuY-NTS_5IpzwH3FfskfFOrnui5O5NlkC"
    })
        .then(function(response) {
                // Handle the results here (response.result has the parsed body).
                console.log("Response", response);
              },
              function(err) { console.error("Execute error", err); });
  }
  gapi.load("client:auth2", function() {
    gapi.auth2.init({client_id: "YOUR_CLIENT_ID"});
  });
</script>
<script>

$(document).ready(function(){
	loadClient();
	setTimeout(function(){
		execute();
		}, 1000);
	
});
</script>
<script type="text/javascript">
  $(document).ready(function() {
 ​
  $.ajax({
  type : "GET",
  dataType : "JSON",
  url : "https://www.googleapis.com/youtube/v3/playlistItems?playlistId=PLuY-NTS_5IpzwH3FfskfFOrnui5O5NlkC&part=snippet&maxResults=8&key=AIzaSyBIGT4K45TpQVfaN7Uta1gxYfRdWdbtFD0",
  contentType : "application/json",
  success : function(jsonData) {
  for (var i = 0; i < jsonData.items.length; i++) {
     var items = jsonData.items[i];
     console.log("title : "+items.snippet.title);
     console.log("videoId : "+"https://youtu.be/"+items.snippet.resourceId.videoId);
     console.log("썸네일 : "+items.snippet.thumbnails.high.url);
  }
  },
  complete : function(data) {
  },
  error : function(xhr, status, error) {
  console.log("유튜브 요청 에러: "+error);
  }
  });
  });
  </script>


<style type="text/css">
.titlemargin{
	margin-bottom: 25px;
	margin-top: 25px;
}
</style>
</head>
<body>
<div class="top_space"  style="height: 220px;"></div>

<!-- 제목 -->
<div id="listApp">

<div class="breadcumb-area">
    <div class="container h-100">
        <div class="row h-100 align-items-center">
            <div class="col-12">
                <div class="bradcumb-title text-center">
                    <h2>하이라이트</h2>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 검색 -->
<div class="breadcumb-nav titlemargin">
     <div class="container">
         <div class="row text-center">
             <div class="col-10">
                 <select id="helightSelect">
                 	<option>1</option>
                 	<option>2</option>
                 </select>&nbsp;
                 <input type="text" size=65 class="input-sm" name="highlightSearch" ref="highlightSearch" v-model="highlightSearch">
             </div>
         </div>
     </div>
</div>
<!-- ****** Breadcumb Area End ****** -->


<!-- ****** Archive Area Start ****** -->
<section class="archive-area section_padding_80">
    <div class="container">
        <div class="row titlemargin" style="border-top: double;">
            <!-- Single Post -->
            <div v-for="vo in">
                <div class="single-post wow fadeInUp" data-wow-delay="0.1s">

					<table class="table" >
						<tr>
							<td width="30%" class="text-center" rowspan="6" style="border: none;" >
								<a href="#">
									<img src="{{thumdnail}}" style="width: 200px;">
										
								</a>
							</td>
							<td width="70%">
								<a href="#">
					 				<h3 style="20px;" class="text-left">제목 ${vo.title }&nbsp;<span style="color:orange;"></span></h3>
					 			</a>
							</td>
						</tr>
						<tr>
							<td class="text-right" style="border: none;">업로드 시간 {{publishedAt }}</td>
						</tr>
					</table>

				</div>
			</div>
		</div>
	</div>            
</section>


<button onclick="loadClient()">load</button>
<button onclick="execute()">execute</button>
<button onclick="search()">search</button>


<div class="col-12">
    <div class="pagination-area d-sm-flex mt-15 text-center">
        <nav aria-label="#">
            <ul class="pagination">
                <li class="page-item" v-if="startPage>1">
                    <a class="page-link" @click="prev()"><i class="fa fa-angle-double-left" aria-hidden="true"></i> 이전</a>
                </li>
                
                <li :class="i===curpage?'page-item active':'page-item'" v-for="i in range(startPage,endPage)">
                    <a class="page-link" @click="pageChange(i)">{{i}}</a>
                </li>
     
                <li class="page-item" v-if="endPage<totalpage">
                    <a class="page-link" @click="next()">다음 <i class="fa fa-angle-double-right" aria-hidden="true"></i></a>
                </li>
            </ul>
        </nav>
        <div class="page-status">
            <p>{{curpage}} page / {{totalpage}} pages</p>
        </div>
    </div>
</div>
            

</div>

<!-- <script type="text/javascript">
    $(document).ready(function() {
        $.ajax({
            type: "GET",
            dataType: "json",
            url: "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&status=&playlistId=PLuY-NTS_5IpzwH3FfskfFOrnui5O5NlkC&key=AIzaSyBIGT4K45TpQVfaN7Uta1gxYfRdWdbtFD0",
            contentType : "application/json",
            success : function(data) {
                data.items.forEach(function(element, index) {
                    $('body').append('<iframe width="560" height="315" src="https://www.youtube.com/embed/'+element.id.videoId+'" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>');
                });
            },
            complete : function(data) {
            },
            error : function(xhr, status, error) {
                console.log("유튜브 요청 에러: "+error);
            }
        });
    });
</script>	 -->
<
<script>
 let listApp=Vue.createApp({
	 //Model => 데이터관리 
	 data(){
		 return  {
			 youtube_list:[],
			 curpage:1,
			 totalpage:0,
			 startPage:0,
			 endPage:0
		 }
	 },
	 mounted(){
		 this.dataRecv()
	 },
	 methods:{
		 prev(){
			 this.curpage=this.startPage-1
			 this.dataRecv()
		 },
		 next(){
			 this.curpage=this.endPage+1
			 this.dataRecv()
		 },
		 pageChange(page){
			 this.curpage=page
			 this.dataRecv()
		 },
		 range(start,end){
			 let arr=[]
			 let len=end-start
			 for(let i=0;i<=len;i++)
			 {
				 arr[i]=start
				 start++;
			 }
			 return arr
		 },
		 dataRecv(){
			 axios.get('../youtube/list_vue.do',{
				params:{
					 page:this.curpage
				}
			 }).then(response=>{
				 console.log(response.data)
				 this.youtube_list=response.data.list
				 this.curpage=response.data.curpage
				 this.totalpage=response.data.totalpage
				 this.startPage=response.data.startPage
				 this.endPage=response.data.endPage
			 }).catch(error=>{
				 console.log(error.response)
			 })
		 }
	 }
 }).mount('#listApp')
</script>
    
</body>
</html>