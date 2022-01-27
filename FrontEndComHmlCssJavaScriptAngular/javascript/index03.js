let a = 1
let b = true

if(a == b) console.log('a==b', true)
if(a === b) console.log('a===b',true)
if(a != b) console.log('')
if(a > b) console.log('')
if(a < b) console.log('')
if(a >= b) console.log('')
if(a <= b) console.log('')

//Descontruct
const pessoa =  {
    nome : 'Douglas',
    idade : 40,
    endereco: {
        rua : 'Av. Brasil',
        numero : 1234,
        cidade : 'Maringá',
        uf : 'PR'
    }
}

const {nome, idade } = pessoa
console.log(nome, idade)

const {nome :n, idade: i } = pessoa
console.log(n, i)

console.log(pessoa.endereco.rua)
const {endereco: {rua: logradouro , numero: nr}} = pessoa
console.log(logradouro, nr)

const {endereco: {rua , numero}} = pessoa
console.log(`${rua}, ${numero}`)
console.log(`${logradouro}, ${nr}`)

const arr = [10, 20, 30, 40]
const [a1, b1, c1, d1] = arr
console.log(a1,b1,d1)

const arr2 = [[10, 20, 30, 40],  ['a1', 'b1', 'c1', 'd1']]
const [ , [ , ,x]] = arr2
console.log(x)

//
function intervalo({min = 0, max = 100}) {
    const res = Math.random() * (max - min) + min
    return Math.floor(res)
}
console.log(
    intervalo({min: 17, max: 20})
)

const valores = {max : 50, min: 30}
console.log(
    intervalo(valores)
)

console.log(
    intervalo({min : 90})
)

function intervalo2([min = 0, max = 100]) {
    if(min > max) [min,max] = [max, min]
    const res = Math.random() * (max - min) + min
    return Math.floor(res)
}

console.log(
    intervalo2([20, 30])
)

console.log(
    intervalo2([20, 30]),
    intervalo2([30, 10])
)

function convertToUpperCase(obj){
    try{
        return `${obj.name.toUpperCase()}!!`
    } catch (error){
       // console.log('ERRO: ', error) 
       if(error instanceof TypeError){
           console.log(
           `Ocorreu um erro ${error.name} do tipo: ${error.message}`
           )
       }
    }
}

const obj = { name : 'Douglas'}
console.log(
    convertToUpperCase(obj)
)

const obj2 = { nome : 'Douglas'}
console.log(
    convertToUpperCase(obj2)
)

function myFunction() { alert('hello world' ) }
console.log(myFunction())