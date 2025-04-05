document.addEventListener('DOMContentLoaded', function() {
    const itemsPerPage = 6;
    const itemCards = document.querySelectorAll('.item-card');
    const totalPages = Math.ceil(itemCards.length / itemsPerPage);
    const paginationContainer = document.querySelector('.pagination');
    
    // Função para mostrar apenas os itens da página atual
    function showPage(page) {
        const startIndex = (page - 1) * itemsPerPage;
        const endIndex = startIndex + itemsPerPage;
        
        itemCards.forEach((card, index) => {
            if (index >= startIndex && index < endIndex) {
                card.style.display = 'block';
            } else {
                card.style.display = 'none';
            }
        });
        
        // Atualiza o número da página ativa
        document.querySelectorAll('.pagination-number').forEach(btn => {
            btn.classList.remove('active');
            if (parseInt(btn.textContent) === page) {
                btn.classList.add('active');
            }
        });
    }
    
    // Cria os botões de número de página dinamicamente
    function setupPagination() {
        // Limpa os botões existentes (exceto as setas)
        const arrows = document.querySelectorAll('.pagination-arrow');
        paginationContainer.innerHTML = '';
        paginationContainer.appendChild(arrows[0]);
        
        // Adiciona os números das páginas
        for (let i = 1; i <= totalPages; i++) {
            const pageBtn = document.createElement('button');
            pageBtn.className = 'pagination-number';
            if (i === 1) pageBtn.classList.add('active');
            pageBtn.textContent = i;
            pageBtn.addEventListener('click', () => showPage(i));
            paginationContainer.appendChild(pageBtn);
        }
        
        paginationContainer.appendChild(arrows[1]);
        
        // Configura os eventos das setas
        document.querySelector('.bi-chevron-left').parentNode.addEventListener('click', () => {
            const currentPage = parseInt(document.querySelector('.pagination-number.active').textContent);
            if (currentPage > 1) showPage(currentPage - 1);
        });
        
        document.querySelector('.bi-chevron-right').parentNode.addEventListener('click', () => {
            const currentPage = parseInt(document.querySelector('.pagination-number.active').textContent);
            if (currentPage < totalPages) showPage(currentPage + 1);
        });
    }
    
    // Inicializa a paginação
    setupPagination();
    showPage(1);
});


// Informações Item
// Botão Voltar
document.querySelector('.back-button').addEventListener('click', function() {
    window.history.back();
});

// Botão Solicitar Doação
document.querySelector('.request-button').addEventListener('click', function() {
    // Lógica para solicitar doação
    alert('Solicitação de doação enviada com sucesso!');
});




