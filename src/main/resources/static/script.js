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

// Botão Cadastrar item
document.addEventListener('DOMContentLoaded', function () {
    // --- Paginação ---
    const itemsPerPage = 6;
    const itemCards = document.querySelectorAll('.item-card');
    const totalPages = Math.ceil(itemCards.length / itemsPerPage);
    const paginationContainer = document.querySelector('.pagination');

    function showPage(page) {
        const startIndex = (page - 1) * itemsPerPage;
        const endIndex = startIndex + itemsPerPage;

        itemCards.forEach((card, index) => {
            card.style.display = (index >= startIndex && index < endIndex) ? 'block' : 'none';
        });

        document.querySelectorAll('.pagination-number').forEach(btn => {
            btn.classList.remove('active');
            if (parseInt(btn.textContent) === page) {
                btn.classList.add('active');
            }
        });
    }

    function setupPagination() {
        const arrows = document.querySelectorAll('.pagination-arrow');
        paginationContainer.innerHTML = '';
        if (arrows[0]) paginationContainer.appendChild(arrows[0]);

        for (let i = 1; i <= totalPages; i++) {
            const pageBtn = document.createElement('button');
            pageBtn.className = 'pagination-number';
            if (i === 1) pageBtn.classList.add('active');
            pageBtn.textContent = i;
            pageBtn.addEventListener('click', () => showPage(i));
            paginationContainer.appendChild(pageBtn);
        }

        if (arrows[1]) paginationContainer.appendChild(arrows[1]);

        const leftArrow = document.querySelector('.bi-chevron-left');
        if (leftArrow) {
            leftArrow.parentNode.addEventListener('click', () => {
                const currentPage = parseInt(document.querySelector('.pagination-number.active').textContent);
                if (currentPage > 1) showPage(currentPage - 1);
            });
        }

        const rightArrow = document.querySelector('.bi-chevron-right');
        if (rightArrow) {
            rightArrow.parentNode.addEventListener('click', () => {
                const currentPage = parseInt(document.querySelector('.pagination-number.active').textContent);
                if (currentPage < totalPages) showPage(currentPage + 1);
            });
        }
    }

    setupPagination();
    showPage(1);

    // --- Botão Voltar ---
    const backButton = document.querySelector('.back-button');
    if (backButton) {
        backButton.addEventListener('click', function () {
            window.history.back();
        });
    }

    // --- Botão Solicitar Doação ---
    const requestButton = document.querySelector('.request-button');
    if (requestButton) {
        requestButton.addEventListener('click', function () {
            alert('Solicitação de doação enviada com sucesso!');
        });
    }

    // --- Botão Cadastrar Item + Modal ---
    const donateBtn = document.getElementById("openModalBtn");
    const modal = document.getElementById("successModal");
    const closeBtn = document.querySelector(".close");

    if (donateBtn && modal) {
        donateBtn.addEventListener("click", function () {
            modal.style.display = "block";

            // Se quiser que envie o formulário depois de 2 segundos:
            setTimeout(() => {
                const form = document.querySelector('form');
                if (form) form.submit();
            }, 2000);
        });
    }

    if (closeBtn && modal) {
        closeBtn.addEventListener("click", function () {
            modal.style.display = "none";
        });
    }

    window.addEventListener("click", function (event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    });
});


document.querySelector('form').addEventListener('submit', function(e) {
    document.getElementById("successModal").style.display = "block";
    setTimeout(() => e.target.submit(), 2000);
});

document.addEventListener('DOMContentLoaded', function () {
    const form = document.querySelector('form');
    const openModalBtn = document.getElementById('openModalBtn');
    const successModalElement = document.getElementById('successModal');
    const successModal = new bootstrap.Modal(successModalElement, { keyboard: false });

    // Exibe o modal se a URL contiver ?success=true
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.get("success") === "true") {
        successModal.show();
    }

    // Exibe o modal ao clicar no botão
    if (openModalBtn) {
        openModalBtn.addEventListener('click', function (event) {
            successModal.show();
        });
    }

    // Valida o formulário antes do envio
    if (form) {
        form.addEventListener('submit', function (event) {
            const categoria = document.querySelector('#categoria').value;
            const tamanho = document.querySelector('[name="tamanho"]').value;
            const cor = document.querySelector('[name="cor"]').value;
            const tipo = document.querySelector('[name="tipo"]').value;
            const imagens = document.querySelector('[name="imagens"]').files.length;

            if (categoria && tamanho && cor && tipo && imagens > 0) {
                // Se quiser enviar com AJAX, faça aqui
                // Caso contrário, o formulário será enviado normalmente

                // Exibe o modal de sucesso
                successModal.show();
            } else {
                alert("Por favor, preencha todos os campos obrigatórios.");
            }
        });
    }
});
