/*console.log('Olá Mundo!')
console.log("Olá Mundo de novo")

var a = 'Olá Mundo'
a=10

console.log(a)

let b = 'outro valor'
b=15

console.log(a)
console.log(b)*/
/*
var a = null
var d = ''
let b = 'Outro Valor'
const valor = 'Valor Unico'
console.log(b)
console.log(valor)

b = {
    nome : 'Virei objeto'
}

console.log(b)

if(true) {
    let c = b;
    console.log(c)
}

console.log(b)
console.log(typeof b)*/
/*
let nome = 'Isael'
let idade = '39'
let cidade = 'Campinas'
let uf = 'SP'

console.log('Olá menu nome é '+ nome + ' tenho ' + idade + ' anos de idade e moro em ' + cidade+'-'+uf)
console.log(`Olá menu nome é ${nome} tenho ${idade} anos de idade e moro em ${cidade}-${uf}`)
*/

const not1 = 1.0
const not2 =  Number('2.0')
console.log(not1,not2)
console.log(Number.isInteger(not1))
console.log(Number.isInteger(not2))


const peso1 = 1.0
const peso2 =  Number('2.0')
console.log(typeof not1, typeof not2)

const avaliacao1 = 8.876
const avaliacao2 = 7.354

const total = avaliacao1 * peso1 + avaliacao2 * peso2
console.log(total)
const media = total / (peso1 + peso2)
console.log(media)
console.log(media.toFixed(2))
console.log(typeof media)
console.log(typeof media.toFixed(2))

console.log(Math.ceil(media))

console.log('A média de João neste semestre foi: ' + media)
console.log('10' + 2)
console.log('10' * 2)
console.log('10' / 2)
console.log('teste' / 2) //Nan - Not a Number
console.log(7 / 0) //Infinity
console.log(0.7 + 0.1)
console.log(109.76423.toFixed(2))
console.log((109).toFixed(2))

const radioG = 35
const radioP = 25

const areaG = Math.PI * Math.pow(radioG, 2)
const areaP = Math.PI * Math.pow(radioP, 2)

console.log(areaG, areaP)
const diference = (areaG - areaP) / Math.PI
console.log(`A diferença entre as areas é de ${diference.toFixed(2)} cm`)

const faculdade = 'Uniciv'
console.log(faculdade)
console.log(faculdade.charAt(3))
console.log(faculdade.charCodeAt(3))
console.log(faculdade.indexOf('i'))
console.log(faculdade.substring(1)) //retorna tudo a partir do indice passado
console.log(faculdade.substring(1,3)) //Retorna tudo dentro do indice passado

console.log('Faculdade '.concat(faculdade))
console.log('Faculdade ' + faculdade)
console.log(`Faculdade ${faculdade}`)

console.log(faculdade.replace('i', '1'))

const nomes = 'Ana, Maria, José, Pedro, João'
console.log(nomes.split(','))

const creditCardNumber = '4004 5484 3214 8424'
let cc = creditCardNumber.split(' ')
console.log(cc)
console.log(cc[2])

let isAtivo = false
console.log(isAtivo)

isAtivo = true
console.log(isAtivo)
isAtivo = 0
console.log(isAtivo)
isAtivo = 1
console.log(isAtivo)
console.log(!!isAtivo)
console.log(!!isAtivo==0)
console.log(!!isAtivo=='')
console.log(!!isAtivo==' ')