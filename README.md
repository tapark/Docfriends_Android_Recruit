# Docfriends_Android_Recruit

- 작업기간 : 8/19(목) ~ 8/21(토)
- 참여인원 : 박태규(tapark)

<코드 구현>
- MainActivity.kt
- home / HomeFragment.kt
- home / HomeAdapter.kt
- home / ExpertAdapter.kt
- home / CompanyAdapter.kt
- home / ViewType.kt

<데이터 모델>
- https://docfriends.github.io/Docfriends_Android_Recruit/api/home.json
- main_api_model / MainService.kt
- main_api_model / MainDto.kt
- main_api_model / PageMapModel.kt
- main_api_model / ConsultModel.kt
- main_api_model / ExpertModel.kt
- main_api_model / CompanyModel.kt
- main_api_model / TagModel.kt
- ~~https://run.mocky.io/v3/388e1a9b-94f0-4eaf-8510-a87a9bd9cc6b~~
- ~~user_api_model / UserService.kt~~
- ~~user_api_model / UserDto.kt~~
- ~~user_api_model / UserModel.kt~~


<구현기능>
- BottomNavigationView로 item별 Fragment 전환
- 가로형 RecyclerView를 포함하는 세로형 RecyclerView
- DiffUtil로 RecyclerView 성능 개선
- 초기데이터 수신 ProgressBar
- 아래로 Swipe하여 최신데이터 수신 (새로고침)

<사용 라이브러리>
 - retrofit2 : API 통신
 - glide : 이미지 로딩
 - swiperefreshlayout : 새로고침 모션

-----------------------------------------------------

안녕하세요

닥프렌즈 Android 개발자 채용에 지원해주셔서 감사합니다.

깃헙 [repository](https://github.com/Docfriends/Docfriends_Android_Recruit)를 fork하신 뒤, 작업하여 폴더에 solution을 넣어 push 해주시면 됩니다.

이 프로젝트의 목적은 Android를 활용해서 UI를 만드는 것 입니다.

전달되는 데이터의 형태를 확인하여 하단의 기능을 작동하는 UI를 만들어주시면 됩니다.

UI구조에 정확한 정답은 없으며, 해당 구조로 만든 이유를 설명할 수 있으면 됩니다.

# Guidelines

* 마감 기한은 과제 공유 메일이 도착한 일주일 후 입니다.
* Project는 빌드가 필수 전제이나, Project가 완벽하지 않아도 됩니다.
* Git을 반드시 사용해주세요.
* 개발 언어는 Kotlin를 사용해주세요.
* 특정 기능에 대한 라이브러리를 쓰셔도 무방합니다.
* 작업을 commit 단위로 나눠주세요
* 질문이 있으실 경우 info@docfriends.com으로 문의 부탁드립니다.

# Example

## 1. 홈

* 홈 탭만 구현하시면 됩니다.
* [홈 데이터(https://docfriends.github.io/Docfriends_Android_Recruit/api/home.json)](https://docfriends.github.io/Docfriends_Android_Recruit/api/home.json) 를 이용하여 화면을 표시 해주세요.
* Toolbar와 BottomBar는 개인이 편한 방식으로 개발 해주세요.
* 아이콘은 resources 폴더의 이미지를 사용해도 됩니다.
* Light Theme는 필수이지만 Drak Theme는 선택적으로 사용하셔도 됩니다.
* json데이터의 expertListPosition과 companyListPosition은 List의 index순서입니다.

<img src="example/home1.jpg" width="300">

* 상담 리스트가 나오는 화면입니다.

<br/>

<img src="example/home1.jpg" width="300">

* 상담 리스트 사이에 전문가 리스트가 나오는 화면입니다.

<br/>

<img src="example/home2.jpg" width="300">

* 상담 리스트 사이에 병원 리스트가 나오는 화면입니다.

<video controls autoplay width="300">
    <source src="example/sample.mp4" type="video/mp4"/>
</video>

* 전체 화면 샘플 영상입니다.

  <br/>

  <img src="example/home_full.jpg" width="300">

  * 전체 리스트 스크린샷 입니다.

