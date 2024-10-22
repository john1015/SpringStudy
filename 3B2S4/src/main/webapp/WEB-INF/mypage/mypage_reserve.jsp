<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <div id="mypageApp">
   <table class="table">
   <tr>
   	<td class="text-center" colspan="8">
   		<h4>예약 관리</h4>
   	</td>
   </tr>
    <tr>
      <th class="text-center">번호</th>
      <th class="text-center"></th>
      <th class="text-center">업체명</th>
      <th class="text-center">예약일</th>
      <th class="text-center">방등급</th>
      <th class="text-center">가격</th>
      <th class="text-center">등록일</th>
      <th class="text-center">비고</th>
    </tr>
    <tr v-for="vo in reserve_list">
      <td class="text-center">{{vo.rno}}</td>
      <td class="text-center">
        <img :src="vo.hvo.poster" style="width: 30px;height: 30px">
      </td>
      <td class="text-center">{{vo.hvo.name}}</td>
      <td class="text-center">{{vo.rday}}</td>
      <td class="text-center">{{vo.rroom}}</td>
      <td class="text-center">{{vo.rprice}}</td>
      <td class="text-center">{{vo.dbday}}</td>
      <td class="text-center">
        <button class="btn-xs btn-success" v-if="vo.isreserve===1" 
        @click="hotelInfo(vo.hno,vo.rno)">예약완료</button>
        <button class="btn-xs btn-danger" v-else >예약대기</button>
      </td>
    </tr>
   </table>
   <table class="table" v-if="isShow">
     <tr>
      <td colspan="3"><h4>호텔 예약 정보</h4></td>
     </tr>
     <tr>
       <td width=30% class="text-center" rowspan="10">
        <img :src="reserve_info.hvo.poster" style="width: 100%">
       </td> 
       <td colspan="2">
         <h4>{{reserve_info.hvo.name}}&nbsp;
          <span style="color:orange;">{{reserve_info.hvo.score}}</span></h4>
       </td>
     </tr>
     <tr>
      <th class="text-center" width=20%>주소</th>
      <td width="50%">{{reserve_info.hvo.address}}</td>
     </tr>
     <tr>
      <th class="text-center" width=20%>가격</th>
      <td width="50%">{{reserve_info.rprice}}</td>
     </tr>
     <tr>
      <th class="text-center" width=20%>예약방등급</th>
      <td width="50%">{{reserve_info.rroom}}</td>
     </tr>
     <tr>
      <th class="text-center" width=20%>예약일</th>
      <td width="50%">{{reserve_info.rday}}</td>
     </tr>
     <tr>
      <th class="text-center" width=20%>신청일</th>
      <td width="50%">{{reserve_info.dbday}}</td>
     </tr>
     <tr>
      <th class="text-center" width=20%>주위야구장</th>
      <td width="50%">{{reserve_info.hvo.location}}</td>
     </tr>
     <tr>
        <td colspan="2" class="text-center">
            <button class="btn-sm btn-info">결제하기</button>&nbsp;
            <button class="btn-sm btn-default">취소</button>
        </td>
    </tr>
   </table>
 </div>
 <script>
  let mypageApp=Vue.createApp({
	  data(){
		  return {
			 reserve_list:[],
			 rno:0,
			 reserve_info:{},
			 isShow:false
		  }
	  },
	  mounted(){
		  // 서버로 전송할 데이터가 없는 경우 params => ?
		  axios.get('../mypage/mypage_reserve_vue.do')
		  .then(response=>{
			  console.log(response.data)
			  this.reserve_list=response.data
		  }).catch(error=>{
			  console.log(error.response)
		  })
	  },
	  methods:{
		  hotelInfo(hno,rno){
			  this.isShow=true
			  axios.get('../mypage/reserve_info_vue.do',{
				  params:{
					  hno:hno,
					  rno:rno
				  }
			  }).then(response=>{
				  console.log(response.data)
				  this.reserve_info=response.data
			  }).catch(error=>{
				  console.log(error.response)
			  })
		  }
	  }
  }).mount("#mypageApp")
 </script>
</body>
</html>