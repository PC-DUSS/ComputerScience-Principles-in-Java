// You're on the right path!
function scramble(message, a, b, c) {
  return message.split('').map((chr, i) => {
    const code = chr.charCodeAt(0)
    switch(i % 3) {
      case 0: return (code + a) % 256
      case 1: return (code + b) % 256
      case 2: return (code + c) % 256
    }
  }).join(' ')
}
