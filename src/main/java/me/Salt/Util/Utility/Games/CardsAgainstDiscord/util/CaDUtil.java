/*
 * Copyright (c) 2017 DevJake
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.Salt.Util.Utility.Games.CardsAgainstDiscord.util;

import me.Salt.Util.Utility.Games.CardsAgainstDiscord.Entity.BlackCard;
import me.Salt.Util.Utility.Games.CardsAgainstDiscord.Entity.WhiteCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CaDUtil {
    private static List<WhiteCard> whiteCards = new ArrayList<>();
    private static List<BlackCard> blackCards = new ArrayList<>();
    
    static {
        //        #######################
        //        ###   WHITE CARDS   ###
        //        #######################
        whiteCards.addAll(Arrays.asList(new WhiteCard("A Gypsy curse."), new WhiteCard("A moment of silence."),
                new WhiteCard("A sausage festival."), new WhiteCard("An honest cop with nothing left to lose."),
                new WhiteCard("Famine."), new WhiteCard("Flesh-eating bacteria."), new WhiteCard("Flying sex snakes."),
                new WhiteCard("Not giving a shit about the Third World."), new WhiteCard("Sexting."),
                new WhiteCard("Shapeshifters."), new WhiteCard("Porn stars."), new WhiteCard("72 virgins."),
                new WhiteCard("A drive-by shooting."), new WhiteCard("A time travel paradox."),
                new WhiteCard("Authentic Mexican cuisine."), new WhiteCard("Bling."), new WhiteCard("Consultants."),
                new WhiteCard("Crippling debt."), new WhiteCard("Daddy issues."),
                new WhiteCard("The Donald Trump Seal of Approval™."),
                new WhiteCard("Dropping a chandelier on your enemies and riding the rope up."),
                new WhiteCard("Former President George W. Bush."), new WhiteCard("Full frontal nudity."),
                new WhiteCard("Hormone injections."), new WhiteCard("Laying an egg."),
                new WhiteCard("Getting naked and watching Nickelodeon."), new WhiteCard("Pretending to care."),
                new WhiteCard("Public ridicule."), new WhiteCard("Sharing needles."), new WhiteCard("Boogers."),
                new WhiteCard("The inevitable heat death of the universe."),
                new WhiteCard("The miracle of childbirth."), new WhiteCard("The Rapture."),
                new WhiteCard("Whipping it out."), new WhiteCard("White privilege."), new WhiteCard("Wifely duties."),
                new WhiteCard("The Hamburglar."), new WhiteCard("AXE Body Spray."),
                new WhiteCard("The Blood of Christ."), new WhiteCard("Horrifying laser hair removal accidents."),
                new WhiteCard("BATMAN!!!"), new WhiteCard("Agriculture."), new WhiteCard("A robust mongoloid."),
                new WhiteCard("Natural selection."), new WhiteCard("Coat hanger abortions."),
                new WhiteCard("Eating all of the cookies before the AIDS bake-sale."),
                new WhiteCard("Michelle Obama's arms."), new WhiteCard("The World of Warcraft."),
                new WhiteCard("Swooping."), new WhiteCard("Obesity."),
                new WhiteCard("A homoerotic volleyball montage."), new WhiteCard("Lockjaw."),
                new WhiteCard("A mating display."), new WhiteCard("Testicular torsion."),
                new WhiteCard("All-you-can-eat shrimp for $4.99."), new WhiteCard("Domino's™ Oreo™ Dessert Pizza."),
                new WhiteCard("Kanye West."), new WhiteCard("Hot cheese."), new WhiteCard("Raptor attacks."),
                new WhiteCard("Taking off your shirt."), new WhiteCard("Smegma."), new WhiteCard("Alcoholism."),
                new WhiteCard("A middle-aged man on roller skates."), new WhiteCard("The Care Bear Stare."),
                new WhiteCard("Bingeing and purging."), new WhiteCard("Oversized lollipops."),
                new WhiteCard("Self-loathing."), new WhiteCard("Children on leashes."),
                new WhiteCard("Half-assed foreplay."), new WhiteCard("The Holy Bible."),
                new WhiteCard("German dungeon porn."), new WhiteCard("Being on fire."),
                new WhiteCard("Teenage pregnancy."), new WhiteCard("Gandhi."),
                new WhiteCard("Leaving an awkward voicemail."), new WhiteCard("Uppercuts."),
                new WhiteCard("Customer service representatives."),
                new WhiteCard("An erection that lasts longer than four hours."), new WhiteCard("My genitals."),
                new WhiteCard("Picking up girls at the abortion clinic."), new WhiteCard("Science."),
                new WhiteCard("Not reciprocating oral sex."), new WhiteCard("Flightless birds."),
                new WhiteCard("A good sniff."), new WhiteCard("Waterboarding."), new WhiteCard("A balanced breakfast."),
                new WhiteCard("Historically black colleges."), new WhiteCard("Actually taking candy from a baby."),
                new WhiteCard("The Make-A-Wish Foundation."), new WhiteCard("A clandestine butt scratch."),
                new WhiteCard("Passive-aggressive Post-it notes."), new WhiteCard("The Chinese gymnastics team."),
                new WhiteCard("Switching to Geico."), new WhiteCard("Peeing a little bit."),
                new WhiteCard("Home video of Oprah sobbing into a Lean Cuisine."),
                new WhiteCard("Nocturnal emissions."), new WhiteCard("The Jews."), new WhiteCard("My humps."),
                new WhiteCard("Powerful thighs."), new WhiteCard("Winking at old people."),
                new WhiteCard("Mr. Clean, right behind you."), new WhiteCard("A gentle caress of the inner thigh."),
                new WhiteCard("Sexual tension."), new WhiteCard("The forbidden fruit."), new WhiteCard("Skeletor."),
                new WhiteCard("Fancy Feast."), new WhiteCard("Being rich."), new WhiteCard("Sweet, sweet vengeance."),
                new WhiteCard("Republicans."), new WhiteCard("A gassy antelope."), new WhiteCard("Natalie Portman."),
                new WhiteCard("Copping a feel."), new WhiteCard("Kamikaze pilots."), new WhiteCard("Sean Connery."),
                new WhiteCard("The homosexual agenda."), new WhiteCard("The hardworking Mexican."),
                new WhiteCard("A falcon with a cap on its head."), new WhiteCard("Altar boys."),
                new WhiteCard("The Kool-Aid Man."), new WhiteCard("Getting so angry that you pop a boner."),
                new WhiteCard("Free samples."), new WhiteCard("A big hoopla about nothing."),
                new WhiteCard("Doing the right thing."), new WhiteCard("The Three-Fifths compromise."),
                new WhiteCard("Lactation."), new WhiteCard("World peace."), new WhiteCard("RoboCop."),
                new WhiteCard("Chutzpah."), new WhiteCard("Justin Bieber."), new WhiteCard("Oompa-Loompas."),
                new WhiteCard("Inappropriate yodeling."), new WhiteCard("Puberty."), new WhiteCard("Ghosts."),
                new WhiteCard("An asymmetric boob job."), new WhiteCard("Vigorous jazz hands."),
                new WhiteCard("Fingering."), new WhiteCard("Glenn Beck catching his scrotum on a curtain hook."),
                new WhiteCard("GoGurt."), new WhiteCard("Police brutality."), new WhiteCard("John Wilkes Booth."),
                new WhiteCard("Preteens."), new WhiteCard("Scalping."),
                new WhiteCard("Stifling a giggle at the mention of Hutus and Tutsis."), new WhiteCard("\"Tweeting\""),
                new WhiteCard("Darth Vader."), new WhiteCard("A sad handjob."),
                new WhiteCard("Exactly what you'd expect."),
                new WhiteCard("Expecting a burp and vomiting on the floor."), new WhiteCard("Adderall™."),
                new WhiteCard("Embryonic stem cells."), new WhiteCard("Tasteful sideboob."),
                new WhiteCard("Panda sex."), new WhiteCard("An icepick lobotomy."), new WhiteCard("Tom Cruise."),
                new WhiteCard("Mouth herpes."), new WhiteCard("Sperm whales."), new WhiteCard("Homeless people."),
                new WhiteCard("Third base."), new WhiteCard("Incest."),
                new WhiteCard("Pac-Man uncontrollably guzzling cum."), new WhiteCard("A mime having a stroke."),
                new WhiteCard("Hulk Hogan."), new WhiteCard("God."), new WhiteCard("Scrubbing under the folds."),
                new WhiteCard("Golden showers."), new WhiteCard("Emotions."),
                new WhiteCard("Licking things to claim them as your own."), new WhiteCard("Pabst Blue Ribbon."),
                new WhiteCard("The placenta."), new WhiteCard("Spontaneous human combustion."),
                new WhiteCard("Friends with benefits."), new WhiteCard("Finger painting."),
                new WhiteCard("Old-people smell."), new WhiteCard("Dying of dysentery."),
                new WhiteCard("My inner demons."), new WhiteCard("A Super Soaker™ full of cat pee."),
                new WhiteCard("Aaron Burr."), new WhiteCard("Cuddling."), new WhiteCard("The chronic."),
                new WhiteCard("Cockfights."), new WhiteCard("Friendly fire."), new WhiteCard("Ronald Reagan."),
                new WhiteCard("A disappointing birthday party."), new WhiteCard("A sassy black woman."),
                new WhiteCard("Mathletes."), new WhiteCard("A tiny horse."), new WhiteCard("William Shatner."),
                new WhiteCard("Riding off into the sunset."), new WhiteCard("An M. Night Shyamalan plot twist."),
                new WhiteCard("Jew-fros."), new WhiteCard("Mutually-assured destruction."),
                new WhiteCard("Pedophiles."), new WhiteCard("Yeast."), new WhiteCard("Grave robbing."),
                new WhiteCard("Eating the last known bison."), new WhiteCard("Catapults."),
                new WhiteCard("Poor people."), new WhiteCard("Forgetting the Alamo."), new WhiteCard("The Hustle."),
                new WhiteCard("The Force."), new WhiteCard("Wiping her butt."), new WhiteCard("Intelligent design."),
                new WhiteCard("Loose lips."), new WhiteCard("AIDS."), new WhiteCard("Pictures of boobs."),
                new WhiteCard("The Übermensch."), new WhiteCard("Sarah Palin."), new WhiteCard("American Gladiators."),
                new WhiteCard("Getting really high."), new WhiteCard("Scientology."), new WhiteCard("Penis envy."),
                new WhiteCard("Praying the gay away."), new WhiteCard("Frolicking."),
                new WhiteCard("Two midgets shitting into a bucket."), new WhiteCard("The KKK."),
                new WhiteCard("Genghis Khan."), new WhiteCard("Crystal meth."), new WhiteCard("Serfdom."),
                new WhiteCard("Stranger danger."), new WhiteCard("A Bop It™."),
                new WhiteCard("Shaquille O'Neal's acting career."), new WhiteCard("Prancing."),
                new WhiteCard("Vigilante justice."), new WhiteCard("Overcompensation."),
                new WhiteCard("Pixelated bukkake."), new WhiteCard("A lifetime of sadness."), new WhiteCard("Racism."),
                new WhiteCard("Dwarf tossing."), new WhiteCard("Sunshine and rainbows."),
                new WhiteCard("A monkey smoking a cigar."), new WhiteCard("Flash flooding."),
                new WhiteCard("Lance Armstrong's missing testicle."), new WhiteCard("Dry heaving."),
                new WhiteCard("The terrorists."), new WhiteCard("Britney Spears at 55."), new WhiteCard("Attitude."),
                new WhiteCard("Breaking out into song and dance."), new WhiteCard("Leprosy."),
                new WhiteCard("Gloryholes."), new WhiteCard("Nipple blades."), new WhiteCard("The heart of a child."),
                new WhiteCard("Puppies!"), new WhiteCard("Waking up half-naked in a Denny's parking lot."),
                new WhiteCard("Dental dams."), new WhiteCard("Toni Morrison's vagina."),
                new WhiteCard("The taint; the grundle; the fleshy fun-bridge."), new WhiteCard("Active listening."),
                new WhiteCard("Ethnic cleansing."), new WhiteCard("The Little Engine That Could."),
                new WhiteCard("The invisible hand."), new WhiteCard("Waiting ‘til marriage."),
                new WhiteCard("Unfathomable stupidity."), new WhiteCard("Euphoria™ by Calvin Klein."),
                new WhiteCard("Re-gifting."), new WhiteCard("Autocannibalism."), new WhiteCard("Erectile dysfunction."),
                new WhiteCard("My collection of high-tech sex toys."), new WhiteCard("The Pope."),
                new WhiteCard("White people."), new WhiteCard("Tentacle porn."), new WhiteCard(
                        "Glenn Beck convulsively vomiting as a brood of crab spiders hatches in his brain and erupts from his tear ducts."),
                new WhiteCard("Too much hair gel."), new WhiteCard("Seppuku."), new WhiteCard("Same-sex ice dancing."),
                new WhiteCard("Cheating in the Special Olympics."), new WhiteCard("Charisma."),
                new WhiteCard("Keanu Reeves."), new WhiteCard("Sean Penn."), new WhiteCard("Nickelback."),
                new WhiteCard("A look-see."), new WhiteCard("Pooping back and forth. Forever."),
                new WhiteCard("Menstruation."), new WhiteCard("Kids with ass cancer."),
                new WhiteCard("A salty surprise."), new WhiteCard("The South."),
                new WhiteCard("The violation of our most basic human rights."),
                new WhiteCard("YOU MUST CONSTRUCT ADDITIONAL PYLONS."), new WhiteCard("Being fabulous."),
                new WhiteCard("Necrophilia."), new WhiteCard("Centaurs."), new WhiteCard("Bill Nye the Science Guy."),
                new WhiteCard("Black people."), new WhiteCard("Chivalry."), new WhiteCard("Lunchables™."),
                new WhiteCard("Bitches."), new WhiteCard("The profoundly handicapped."),
                new WhiteCard("Heartwarming orphans."), new WhiteCard("MechaHitler."), new WhiteCard("Fiery poops."),
                new WhiteCard("Another goddamn vampire movie."), new WhiteCard("Tangled Slinkys."),
                new WhiteCard("The true meaning of Christmas."), new WhiteCard("Estrogen."),
                new WhiteCard("A zesty breakfast burrito."), new WhiteCard("That thing that electrocutes your abs."),
                new WhiteCard("Passing a kidney stone."), new WhiteCard("A bleached asshole."),
                new WhiteCard("Michael Jackson."), new WhiteCard("Cybernetic enhancements."),
                new WhiteCard("Guys who don't call."), new WhiteCard("Smallpox blankets."),
                new WhiteCard("Masturbation."), new WhiteCard("Classist undertones."), new WhiteCard("Queefing."),
                new WhiteCard("Concealing a boner."), new WhiteCard("Edible underpants."), new WhiteCard("Viagra."),
                new WhiteCard("Soup that is too hot."), new WhiteCard("Muhammad (Praise Be Unto Him)."),
                new WhiteCard("Surprise sex!"), new WhiteCard("Five-Dollar Footlongs™."),
                new WhiteCard("Drinking alone."), new WhiteCard("Dick fingers."),
                new WhiteCard("Multiple stab wounds."), new WhiteCard("Soiling oneself."),
                new WhiteCard("Child abuse."), new WhiteCard("Anal beads."), new WhiteCard("Civilian casualties."),
                new WhiteCard("Pulling out."), new WhiteCard("Robert Downey, Jr."), new WhiteCard("Horse meat."),
                new WhiteCard("A really cool hat."), new WhiteCard("Kim Jong-il."), new WhiteCard("A stray pube."),
                new WhiteCard("Jewish fraternities."), new WhiteCard("The token minority."),
                new WhiteCard("Doin' it in the butt."), new WhiteCard("Feeding Rosie O'Donnell."),
                new WhiteCard("Teaching a robot to love."), new WhiteCard("A can of whoop-ass."),
                new WhiteCard("A windmill full of corpses."), new WhiteCard("Count Chocula."),
                new WhiteCard("Wearing underwear inside-out to avoid doing laundry."), new WhiteCard("A death ray."),
                new WhiteCard("The glass ceiling."), new WhiteCard("A cooler full of organs."),
                new WhiteCard("The American Dream."), new WhiteCard("Keg stands."),
                new WhiteCard("When you fart and a little bit comes out."), new WhiteCard("Take-backsies."),
                new WhiteCard("Dead babies."), new WhiteCard("Foreskin."), new WhiteCard("Saxophone solos."),
                new WhiteCard("Italians."), new WhiteCard("A fetus."),
                new WhiteCard("Firing a rifle into the air while balls deep in a squealing hog."),
                new WhiteCard("Dick Cheney."), new WhiteCard("Amputees."), new WhiteCard("Eugenics."),
                new WhiteCard("My relationship status."), new WhiteCard("Christopher Walken."), new WhiteCard("Bees?"),
                new WhiteCard("Harry Potter erotica."), new WhiteCard("College."),
                new WhiteCard("Getting drunk on mouthwash."), new WhiteCard("Nazis."),
                new WhiteCard("8 oz. of sweet Mexican black-tar heroin."),
                new WhiteCard("Stephen Hawking talking dirty."), new WhiteCard("Dead parents."),
                new WhiteCard("Object permanence."), new WhiteCard("Opposable thumbs."),
                new WhiteCard("Racially-biased SAT questions."), new WhiteCard("Jibber-jabber."),
                new WhiteCard("Chainsaws for hands."), new WhiteCard("Nicolas Cage."),
                new WhiteCard("Child beauty pageants."), new WhiteCard("Explosions."), new WhiteCard("Sniffing glue."),
                new WhiteCard("Glenn Beck being harried by a swarm of buzzards."), new WhiteCard("Repression."),
                new WhiteCard("Roofies."), new WhiteCard("My vagina."), new WhiteCard("Assless chaps."),
                new WhiteCard("A murder most foul."), new WhiteCard("Giving 110 percent."),
                new WhiteCard("Her Royal Highness, Queen Elizabeth II."), new WhiteCard("The Trail of Tears."),
                new WhiteCard("Being marginalized."), new WhiteCard("Goblins."), new WhiteCard("Hope."),
                new WhiteCard("The Rev. Dr. Martin Luther King, Jr."), new WhiteCard("A micropenis."),
                new WhiteCard("My soul."), new WhiteCard("A hot mess."), new WhiteCard("Vikings."),
                new WhiteCard("Hot people."), new WhiteCard("Seduction."), new WhiteCard("An Oedipus complex."),
                new WhiteCard("Geese."), new WhiteCard("Global warming."), new WhiteCard("New Age music."),
                new WhiteCard("Hot Pockets."), new WhiteCard("Making a pouty face."),
                new WhiteCard("Vehicular manslaughter."), new WhiteCard("Women's suffrage."),
                new WhiteCard("A defective condom."), new WhiteCard("Judge Judy."), new WhiteCard("African children."),
                new WhiteCard("The Virginia Tech Massacre."), new WhiteCard("Barack Obama."),
                new WhiteCard("Asians who aren't good at math."), new WhiteCard("Elderly Japanese men."),
                new WhiteCard("Exchanging pleasantries."), new WhiteCard("Heteronormativity."),
                new WhiteCard("Parting the Red Sea."), new WhiteCard("Arnold Schwarzenegger."),
                new WhiteCard("Road head."), new WhiteCard("Spectacular abs."), new WhiteCard("Figgy pudding."),
                new WhiteCard("A mopey zoo lion."), new WhiteCard("A bag of magic beans."),
                new WhiteCard("Poor life choices."), new WhiteCard("My sex life."), new WhiteCard("Auschwitz."),
                new WhiteCard("A snapping turtle biting the tip of your penis."),
                new WhiteCard("A thermonuclear detonation."), new WhiteCard("The clitoris."),
                new WhiteCard("The Big Bang."), new WhiteCard("Land mines."),
                new WhiteCard("Friends who eat all the snacks."), new WhiteCard("Goats eating cans."),
                new WhiteCard("The Dance of the Sugar Plum Fairy."),
                new WhiteCard("Jerking off into a pool of children's tears."), new WhiteCard("Man meat."),
                new WhiteCard("Me time."), new WhiteCard("The Underground Railroad."),
                new WhiteCard("Poorly-timed Holocaust jokes."), new WhiteCard("A sea of troubles."),
                new WhiteCard("Lumberjack fantasies."), new WhiteCard("Morgan Freeman's voice."),
                new WhiteCard("Women in yogurt commercials."), new WhiteCard("Natural male enhancement."),
                new WhiteCard("Being a motherfucking sorcerer."), new WhiteCard("Genital piercings."),
                new WhiteCard("Passable transvestites."), new WhiteCard("Sexy pillow fights."), new WhiteCard("Balls."),
                new WhiteCard("Grandma."), new WhiteCard("Friction."), new WhiteCard("Party poopers."),
                new WhiteCard("Farting and walking away."), new WhiteCard("Being a dick to children."),
                new WhiteCard("Booby-trapping the house to foil burglars."),
                new WhiteCard("The Tempur-Pedic Swedish Sleep System™."), new WhiteCard("Dying."),
                new WhiteCard("Hurricane Katrina."), new WhiteCard("The gays."), new WhiteCard("The folly of man."),
                new WhiteCard("Men."), new WhiteCard("The Amish."), new WhiteCard("Pterodactyl eggs."),
                new WhiteCard("Team-building exercises."), new WhiteCard("A brain tumor."),
                new WhiteCard("Cards Against Humanity."), new WhiteCard("Fear itself."), new WhiteCard("Lady Gaga."),
                new WhiteCard("The milk man."), new WhiteCard("A foul mouth."), new WhiteCard("A big black dick."),
                new WhiteCard("A beached whale."), new WhiteCard("A bloody pacifier."),
                new WhiteCard("A crappy little hand."), new WhiteCard("A low standard of living."),
                new WhiteCard("A nuanced critique."), new WhiteCard("Panty raids."),
                new WhiteCard("A passionate Latino lover."), new WhiteCard("A rival dojo."),
                new WhiteCard("A web of lies."), new WhiteCard("A woman scorned."), new WhiteCard("Clams."),
                new WhiteCard("Apologizing."), new WhiteCard("Appreciative snapping."),
                new WhiteCard("Neil Patrick Harris."), new WhiteCard("Beating your wives."),
                new WhiteCard("Being a dinosaur."), new WhiteCard("Shaft."), new WhiteCard("Bosnian chicken farmers."),
                new WhiteCard("Nubile slave boys."), new WhiteCard("Carnies."),
                new WhiteCard("Coughing into a vagina."), new WhiteCard("Suicidal thoughts."),
                new WhiteCard("Dancing with a broom."), new WhiteCard("Deflowering the princess."),
                new WhiteCard("Dorito breath."), new WhiteCard("Eating an albino."),
                new WhiteCard("Enormous Scandinavian women."), new WhiteCard("Fabricating statistics."),
                new WhiteCard("Finding a skeleton."), new WhiteCard("Gandalf."),
                new WhiteCard("Genetically engineered super-soldiers."), new WhiteCard("George Clooney's musk."),
                new WhiteCard("Getting abducted by Peter Pan."), new WhiteCard("Getting in her pants, politely."),
                new WhiteCard("Gladiatorial combat."), new WhiteCard("Good grammar."), new WhiteCard("Hipsters."),
                new WhiteCard("Historical revisionism."), new WhiteCard("Insatiable bloodlust."),
                new WhiteCard("Jafar."), new WhiteCard("Jean-Claude Van Damme."), new WhiteCard("Just the tip."),
                new WhiteCard("Mad hacky-sack skills."), new WhiteCard("Leveling up."),
                new WhiteCard("Literally eating shit."), new WhiteCard("Making the penises kiss."),
                new WhiteCard("Media coverage."), new WhiteCard("Medieval Times Dinner and Tournament."),
                new WhiteCard("Moral ambiguity."), new WhiteCard("My machete."),
                new WhiteCard("One thousand Slim Jims."), new WhiteCard("Ominous background music."),
                new WhiteCard("Overpowering your father."), new WhiteCard("Pistol-whipping a hostage."),
                new WhiteCard("Quiche."), new WhiteCard("Quivering jowls."), new WhiteCard("Revenge fucking."),
                new WhiteCard("Ripping into a man's chest and pulling out his still-beating heart."),
                new WhiteCard("Ryan Gosling riding in on a white horse."), new WhiteCard("Santa Claus."),
                new WhiteCard("Scrotum tickling."), new WhiteCard("Sexual humiliation."),
                new WhiteCard("Sexy Siamese twins."), new WhiteCard("Slow motion."), new WhiteCard("Space muffins."),
                new WhiteCard("Statistically validated stereotypes."), new WhiteCard("Sudden Poop Explosion Disease."),
                new WhiteCard("The boners of the elderly."), new WhiteCard("The economy."),
                new WhiteCard("The Fanta girls."), new WhiteCard("The Gulags."),
                new WhiteCard("The harsh light of day."), new WhiteCard("The hiccups."),
                new WhiteCard("The shambling corpse of Larry King."), new WhiteCard("The four arms of Vishnu."),
                new WhiteCard("Being a busy adult with many important things to do."), new WhiteCard("Tripping balls."),
                new WhiteCard("Words, words, words."), new WhiteCard("Zeus's sexual appetites.")));
        //        #######################
        //        ###   BLACK CARDS   ###
        //        #######################
        blackCards.addAll(Arrays.asList(new BlackCard("TSA guidelines now prohibit __________ on airplanes.", 1),
                new BlackCard("It's a pity that kids these days are all getting involved with __________.", 1),
                new BlackCard(
                        "In 1,000 years, when paper money is but a distant memory, __________ will be our currency.",
                        1),
                new BlackCard("Major League Baseball has banned __________ for giving players an unfair advantage.", 1),
                new BlackCard("What is Batman's guilty pleasure?", 1),
                new BlackCard("Next from J.K. Rowling: Harry Potter and the Chamber of __________.", 1),
                new BlackCard("I'm sorry, Professor, but I couldn't complete my homework because of __________.", 1),
                new BlackCard("What did I bring back from Mexico?", 1),
                new BlackCard("__________? There's an app for that.", 1),
                new BlackCard("__________. Betcha can't have just one!", 1), new BlackCard("What's my anti-drug?", 1),
                new BlackCard(
                        "While the United States raced the Soviet Union to the moon, the Mexican government funneled millions of pesos into research on __________.",
                        1), new BlackCard(
                        "In the new Disney Channel Original Movie, Hannah Montana struggles with __________ for the first time. ",
                        1), new BlackCard("What's my secret power?", 1), new BlackCard("What's the new fad diet?", 1),
                new BlackCard("What did Vin Diesel eat for dinner?", 1),
                new BlackCard("When Pharaoh remained unmoved, Moses called down a Plague of __________.", 1),
                new BlackCard("How am I maintaining my relationship status?", 1),
                new BlackCard("What's the crustiest?", 1),
                new BlackCard("In L.A. County Jail, word is you can trade 200 cigarettes for __________.", 1),
                new BlackCard("After the earthquake, Sean Penn brought __________ to the people of Haiti.", 1),
                new BlackCard("Instead of coal, Santa now gives the bad children __________.", 1), new BlackCard(
                        "Life for American Indians was forever changed when the White Man introduced them to __________.",
                        1),
                new BlackCard("What's Teach for America using to inspire inner city students to succeed?", 1),
                new BlackCard("Maybe she's born with it. Maybe it's __________.", 1),
                new BlackCard("In Michael Jackson's final moments, he thought about __________.", 1),
                new BlackCard("White people like __________.", 1), new BlackCard("Why do I hurt all over?", 1),
                new BlackCard("A romantic, candlelit dinner would be incomplete without __________.", 1),
                new BlackCard("What will I bring back in time to convince people that I am a powerful wizard?", 1),
                new BlackCard("BILLY MAYS HERE FOR __________.", 1),
                new BlackCard("The class field trip was completely ruined by __________.", 1),
                new BlackCard("What's a girl's best friend?", 1),
                new BlackCard("Dear Abby, I'm having some trouble with __________ and would like your advice.", 1),
                new BlackCard("When I am President of the United States, I will create the Department of __________.",
                        1), new BlackCard("What are my parents hiding from me?", 1),
                new BlackCard("What never fails to liven up the party?", 1),
                new BlackCard("What gets better with age?", 1), new BlackCard("__________: Good to the last drop.", 1),
                new BlackCard("I got 99 problems but __________ ain't one.", 1),
                new BlackCard("__________. It's a trap!", 1),
                new BlackCard("MTV's new reality show features eight washed-up celebrities living with __________.", 1),
                new BlackCard("What would grandma find disturbing, yet oddly charming?", 1),
                new BlackCard("What's the most emo?", 1),
                new BlackCard("During sex, I like to think about __________.", 1),
                new BlackCard("What ended my last relationship?", 1), new BlackCard("What's that sound?", 1),
                new BlackCard("__________. That's how I want to die.", 1), new BlackCard("Why am I sticky?", 1),
                new BlackCard("What's the next Happy Meal toy?", 1),
                new BlackCard("What's there a ton of in heaven?", 1), new BlackCard(
                        "I do not know with what weapons World War III will be fought, but World War IV will be fought with __________.",
                        1), new BlackCard("What will always get you laid?", 1),
                new BlackCard("__________: Kid-tested, mother-approved.", 1),
                new BlackCard("Why can't I sleep at night?", 1), new BlackCard("What's that smell?", 1),
                new BlackCard("What helps Obama unwind?", 1), new BlackCard(
                        "This is the way the world ends / This is the way the world ends / Not with a bang but with __________.",
                        1), new BlackCard("Coming to Broadway this season, __________: The Musical.", 1),
                new BlackCard("Anthropologists have recently discovered a primitive tribe that worships __________.",
                        1), new BlackCard("But before I kill you, Mr. Bond, I must show you __________.", 1),
                new BlackCard("Studies show that lab rats navigate mazes 50% faster after being exposed to __________.",
                        1), new BlackCard("Next on ESPN2: The World Series of __________.", 1),
                new BlackCard("When I am a billionaire, I shall erect a 50-foot statue to commemorate __________.", 1),
                new BlackCard(
                        "In an attempt to reach a wider audience, the Smithsonian Museum of Natural History has opened an interactive exhibit on __________.",
                        1), new BlackCard("War! What is it good for?", 1),
                new BlackCard("What gives me uncontrollable gas?", 1),
                new BlackCard("What do old people smell like?", 1), new BlackCard("What am I giving up for Lent?", 1),
                new BlackCard("Alternative medicine is now embracing the curative powers of __________.", 1),
                new BlackCard("What did the US airdrop to the children of Afghanistan?", 1),
                new BlackCard("What does Dick Cheney prefer?", 1), new BlackCard(
                        "During Picasso's often-overlooked Brown Period, he produced hundreds of paintings of __________.",
                        1), new BlackCard("What don't you want to find in your Chinese food?", 1),
                new BlackCard("I drink to forget __________.", 1), new BlackCard("__________. High five, bro.", 1),
                new BlackCard("He who controls __________ controls the world.", 1),
                new BlackCard("The CIA now interrogates enemy agents by repeatedly subjecting them to __________.", 1),
                new BlackCard(
                        "In Rome, there are whisperings that the Vatican has a secret room devoted to __________.", 1),
                new BlackCard("Science will never explain the origin of __________.", 1),
                new BlackCard("When all else fails, I can always masturbate to __________.", 1),
                new BlackCard("I learned the hard way that you can't cheer up a grieving friend with __________.", 1),
                new BlackCard(
                        "In its new tourism campaign, Detroit proudly proclaims that it has finally eliminated __________.",
                        1), new BlackCard(
                        "The socialist governments of Scandinavia have declared that access to __________ is a basic human right.",
                        1),
                new BlackCard("In his new self-produced album, Kanye West raps over the sounds of __________.", 1),
                new BlackCard("What's the gift that keeps on giving?", 1), new BlackCard(
                        "This season on Man vs. Wild, Bear Grylls must survive in the depths of the Amazon with only __________ and his wits. ",
                        1), new BlackCard("When I pooped, what came out of my butt?", 1), new BlackCard(
                        "In the distant future, historians will agree that __________ marked the beginning of America's decline.",
                        1), new BlackCard("What has been making life difficult at the nudist colony?", 1),
                new BlackCard("And I would have gotten away with it, too, if it hadn't been for __________.", 1),
                new BlackCard("What brought the orgy to a grinding halt?", 1)));
    }
    
    public static List<WhiteCard> getWhiteCards() {
        return whiteCards;
    }
    
    public static List<BlackCard> getBlackCards() {
        return blackCards;
    }
    
    public static WhiteCard getRandomWhiteCard() {
        return whiteCards.get(ThreadLocalRandom.current().nextInt(0, whiteCards.size())); //Random
    }
    
    public static BlackCard getRandomBlackCard() {
        return blackCards.get(ThreadLocalRandom.current().nextInt(0, blackCards.size()));
    }
    
    public static List<WhiteCard> getRandomWhiteCards(int amount) {
        if (amount > whiteCards.size()) return null;
        List<WhiteCard> cards = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            cards.add(getRandomWhiteCard());
        }
        return cards;
    }
    
    public static List<BlackCard> getRandomBlackCards(int amount) {
        if (amount > blackCards.size()) return null;
        List<BlackCard> cards = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            cards.add(getRandomBlackCard());
        }
        return cards;
    }
}
