function showLibrary() {
    const contentArea = document.getElementById('content-area');
    contentArea.innerHTML = '';
    const heading = document.createElement('h1');
    heading.textContent = 'Thư viện';
    contentArea.appendChild(heading);
}
