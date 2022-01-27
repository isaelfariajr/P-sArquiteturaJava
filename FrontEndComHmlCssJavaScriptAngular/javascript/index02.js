const valores = [5 , 7.8 , 6, 9 , 10]

console.log(valores)
console.log(valores.length)
console.log(valores[3])
console.log(valores[9])

valores[2] = 8
console.log(valores)

valores[9] = 30
console.log(valores)
console.log(valores.length)

valores.push(35) //Add elemento no final do array
console.log(valores)

valores.pop() //Remove o ultimo elemento
console.log(valores) 

//Deleta uma posição definida
delete valores[4] 
console.log(valores) 
console.log(typeof valores) 

//Remove o primeiro elemento
valores.shift() 
console.log(valores) 

// Adiciona no primeiro elemento
valores.unshift(5) 
console.log(valores) 

//Adiciona a partir do indice 'i' sem remover/Removendo os valores que serão passados na sequencia
valores.splice(4, 0, 'João', 'Maria', 'Jose')
console.log(valores) 

//Adiciona a partir do indice 'i' sem remover/Removendo os valores que serão passados na sequencia
valores.splice(4, 1, 'Pedro')
console.log(valores) 

//Monta um novo array, a partir de um existente apartir da posição definida
let novo = valores.splice(4) 
console.log(novo) 

//Monta um novo array, a partir de um existente utilizando o intervalo definido
let novo2 = valores.splice(4,7) 
console.log(novo2) 

const aprovados = ['João', 'Pedro', 'Daniel', 'Ronaldo', 'Cesar', 'Maria']

//Forma tradicional
aprovados.forEach( function(nome, i){
    console.log(`Nome do aprovado: ${i+1} ${nome}`)

})

//Arrow function
aprovados.forEach(nome =>{
    console.log(`Outra formar de exibir os aprovados : ${nome}`)

})

//Armazenar uma função em uma variavel
const exibirAprovados = aprovado => console.log('Function variable: ' + aprovado)
aprovados.forEach(exibirAprovados)

const numbers = [1, 2, 3, 4 , 5, 6]

let results = numbers.map(function(e){
    return e * 2
})

console.log(results)

const sum10 = el => el +2
const triple = el => el * 3
const coinBRL = el => `R$ ${parseFloat(el).toFixed(2).replace('.',',')}`

results = numbers.map(sum10).map(triple).map(coinBRL);

console.log(results)

const cart = [
    '{ "name" : "Arroz", "price": 35.99 }',
    '{ "name" : "Feijão", "price": 9.87 }',
    '{ "name" : "Batata", "price": 6.74 }',
    '{ "name" : "Macarrão", "price": 14.35 }',
    '{ "name" : "Sal", "price": 6.37 }',
    '{ "name" : "Ovos", "price": 11.21  }'
]

console.log(cart)

const jsonParse = json => JSON.parse(json)
const price = product => product.price

const result = cart.map(jsonParse).map(price)
console.log(result)

const name = product => product.name
const result2 = cart.map(jsonParse).map(name)
console.log(result2)

//Filter
const caro = product => product.price >= 10.00
const result3= cart.map(jsonParse).filter(caro)
console.log(result3)

const cart2 = [
    { name : "Arroz", price: 35.99, estoque : true},
    { name : "Feijão", price: 9.87, estoque : true},
    { name : "Batata", price: 6.74, estoque : true },
    { name : "Macarrão", price: 14.35, estoque : true },
    { name : "Sal", price: 6.37, estoque : true },
    { name : "Ovos", price: 11.21, estoque : false  }
]

const caro2 = product => product.price >= 9.88
const result4= cart.filter(caro)
console.log(result4)

//Somar uma lista com stream
const lista = cart2.map(p => p.price).reduce(function(inicial, atual){
    console.log(inicial, atual)
    return inicial + atual
})
console.log(lista)

const tax = 5.47

const lista2 = cart2.map(p => p.price).reduce(function(inicial, atual){
    console.log(inicial, atual)
    return inicial + atual
}, tax)
console.log(lista2)

const temEstoque = (resulta, estoque) => resulta && estoque
console.log(cart2.map(p => p.estoque).reduce(temEstoque))

const temEstoque2 = (resulta, estoque) => resulta|| estoque
console.log(cart2.map(p => p.estoque).reduce(temEstoque2))
