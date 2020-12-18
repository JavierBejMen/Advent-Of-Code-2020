using System;
using System.IO;
using System.Linq;
using System.Collections.Generic;
using System.Text.RegularExpressions;
using Microsoft.VisualBasic.FileIO;

namespace Day_4
{
    public static class Program
    {
        public static IEnumerable<Dictionary<string, string>> GetSplittedPassports(this StreamReader reader)
	    {
            Dictionary<string, string> passport = new Dictionary<string, string>();
            string line;
            
            while ( (line = reader.ReadLine()) != null)
            {
                if(line.Length != 0)
                {
                    var dict = line.Split(new[] {' '}, StringSplitOptions.RemoveEmptyEntries)
                        .Select(part => part.Split(':'))
                        .ToDictionary(split => split[0], split => split[1]);

                    passport = passport.Concat(dict).ToDictionary(kvp => kvp.Key, kvp => kvp.Value);
                }else
                {   
                    yield return passport;
                    passport.Clear();
                    continue;
                }
            }

            yield return passport;
            passport.Clear();
	    }

        static void Main(string[] args)
        {
            Dictionary<string, Func<string, bool>> referencePassport = new Dictionary<string, Func<string, bool>>();
            referencePassport.Add("byr", value => {
                Regex regex = new Regex(@"^\d{4}$",RegexOptions.Compiled);
                bool check = false;
                if (regex.Matches(value).Count == 1){
                    int intValue = int.Parse(value);
                    // Console.WriteLine("Regex passed: " + intValue);
                    if (intValue >= 1920 && intValue <= 2020)
                        check = true;
                }
                return check;
            });

            referencePassport.Add("iyr", value => {
                Regex regex = new Regex(@"^\d{4}$",RegexOptions.Compiled);
                bool check = false;
                if (regex.Matches(value).Count == 1){
                    int intValue = int.Parse(value);
                    if (intValue >= 2010 && intValue <= 2020)
                        check = true;
                }
                return check;
            });

            referencePassport.Add("eyr", value => {
                Regex regex = new Regex(@"^\d{4}$",RegexOptions.Compiled);
                bool check = false;
                if (regex.Matches(value).Count == 1){
                    int intValue = int.Parse(value);
                    //Console.WriteLine("Regex passed: " + intValue);
                    if (intValue >= 2020 && intValue <= 2030)
                        check = true;
                }
                return check;
            });

            referencePassport.Add("hgt", value => {
                Regex regex1 = new Regex(@"^\d{3}cm$",RegexOptions.Compiled);
                Regex regex2 = new Regex(@"^\d{2}in$",RegexOptions.Compiled);
                bool check = false;
                if (regex1.Matches(value).Count == 1){
                    int intValue = int.Parse(value.Substring(0,3));
                    if (intValue >= 150 && intValue <= 193)
                        check = true;
                }else{
                    int intValue = int.Parse(value.Substring(0,2));
                    if (intValue >= 59 && intValue <= 76)
                        check = true;
                }
                return check;
            });
            
            referencePassport.Add("hcl", value => {
                Regex regex = new Regex(@"^\#[0-9a-f]{6}$", RegexOptions.Compiled);
                bool check = false;
                if (regex.Matches(value).Count == 1)
                    check = true;
                return check;
            });
            
            referencePassport.Add("ecl", value => {
                Regex regex = new Regex(@"^amb|blu|brn|gry|grn|hzl|oth$", RegexOptions.Compiled);
                bool check = false;
                if (regex.Matches(value).Count == 1)
                    check = true;
                return check;
            });

            referencePassport.Add("pid", value => {
                Regex regex = new Regex(@"^\d{9}$", RegexOptions.Compiled);
                bool check = false;
                if (regex.Matches(value).Count == 1)
                    check = true;
                return check;
            });
            

            int goodPassports = 0;
            int goodPassports2 = 0;
            int i = 0;
            using (var sr = new StreamReader("input.txt"))
            {   
                foreach (var passport in sr.GetSplittedPassports())
                {
                    bool check = true;
                    bool check2 = true;
                    
                    foreach (var field in referencePassport){
                        if (!passport.ContainsKey(field.Key))
                        {
                            check = false;
                            check2 = false;
                        }else if (!field.Value(passport[field.Key]))
                        {
                            Console.WriteLine("{0} failed on {1}:{2}", i, field.Key, passport[field.Key]);
                            check2 = false;
                        }              
                    }

                    if (check) goodPassports++;
                    if (check2) goodPassports2++;
                    
                    i++;
                }                
               
            }

            Console.WriteLine(goodPassports);
            Console.WriteLine(goodPassports2);
        }
    }
}
