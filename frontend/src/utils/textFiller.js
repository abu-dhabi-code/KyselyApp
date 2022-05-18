
const row = 'ABASDASDASDVXCSDSADASABASDASDASDVXCSDSADASABASDASDASDVXCSDSADASABASDASDASDVXCSDSADASSDSADSDASDKSMKDMSKMKSABASDASDASDVXCSDSADASABASDASDASDVXCSDSADASABASDASDASDVXCSDSADASABASDASDASDVXCSDSADASSDSADSDASDKSMKDMSKMKS';
/**
 * @param {number} rows - to create
 * @returns {string} - pattern text
 */
export default function textFiller(rows) {
  const arr = Array(rows);
  for (let i = 0; i < rows; i++)
    arr[i] = row;

  return arr.join('\n');
}

