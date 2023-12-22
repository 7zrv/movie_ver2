document.addEventListener('DOMContentLoaded', function() {
    // 초기 탭 설정
    showTabContent('booking');

    // 탭 클릭 이벤트 등록
    const tabs = document.querySelectorAll('.tab-menu a');
    tabs.forEach(tab => {
        tab.addEventListener('click', function(e) {
            e.preventDefault();
            const tabId = this.getAttribute('href').substring(1);
            showTabContent(tabId);
        });
    });
});

function showTabContent(tabId) {
    // 모든 탭 숨기기
    const tabContents = document.querySelectorAll('.tab-content');
    tabContents.forEach(content => {
        content.style.display = 'none';
    });

    // 선택된 탭 보이기
    const selectedTab = document.getElementById(tabId);
    if (selectedTab) {
        selectedTab.style.display = 'block';
    }
}
