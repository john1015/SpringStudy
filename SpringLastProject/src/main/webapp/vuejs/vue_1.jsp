<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="text-center">
				<my-button v-bind:btn="btn" @click="btnClick1" ></my-button>
				<my-button1 v-bind:btn1="btn1" @click="btnClick2" ></my-button1>
				<my-button2 v-bind:btn2="btn2" @click="btnClick3" ></my-button2>
			</div>
		</div>
	</div>
	
	<script>
	// 페이지 / 카드 출력 => Component
		const mybtn={
				props:['btn'],
				templete:`<button class="btn-sm btn-danger">{{btn}}</button>`,
				methods:{
					btnClick1(){
						this.$parent.btnClick1()
					}
				}
		}
		const mybtn1={
				props:['btn1'],
				templete:`<button class="btn-sm btn-success">{{btn1}}</button>`
		}
		const mybtn2={
				props:['btn2'],
				templete:`<button class="btn-sm btn-success">{{btn2}}</button>`
		}
		let app=Vue.createApp({
			// 재사용
			data(){
				return {
					btn:'로그인',
					btn1:'취소',
					btn1:'회원가입',
					message:'로그인 버튼 클릭'
				}
			},
			methods:{
				btnClick1(){
					alert(this.message)
				}
			},
			// child => parent
			components:{
				'my-button':mybtn,
				'my-button1':mybtn1,
				'my-button2':mybtn2
			}
		}).mount('.conatiner')
	</script>
</body>
</html>