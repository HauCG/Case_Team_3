// Thay đổi dựa vào lựa chọn
function changeContent(section) {
    switch(section) {
        case 'home':
            showHome();
            break;
        case 'shorts':
            showShorts();
            break;
        case 'subscriptions':
            showSubscriptions();
            break;
        case 'library':
            showLibrary();
            break;
        default:
            const contentArea = document.getElementById('content-area');
            contentArea.innerHTML = '';


            const heading = document.createElement('h1');
            heading.textContent = section.charAt(0).toUpperCase() + section.slice(1);
            contentArea.appendChild(heading);
    }
}


document.addEventListener('DOMContentLoaded', function() {
    // DOMContentLoaded để đảm bảo rằng tất cả các phần tử đã được tạo trước khi thêm sự kiện


    const navLinks = document.querySelectorAll('[data-section]');
    // Lấy tất cả các phần tử có thuộc tính data-section

    // Thêm sự kiện click cho mỗi liên kết trong danh sách navLinks
    navLinks.forEach(link => {


        link.addEventListener('click', function(e) {

            // Ngừng hành động mặc định của sự kiện (chuyển trang)
            e.preventDefault();

            // Lấy giá trị của thuộc tính 'data-section' từ liên kết nhấn vào
            const section = this.getAttribute('data-section');

            // Gọi lại hàm thay đổi dựa vào lựa chọn ở trên
            changeContent(section);
        });
    });
});
