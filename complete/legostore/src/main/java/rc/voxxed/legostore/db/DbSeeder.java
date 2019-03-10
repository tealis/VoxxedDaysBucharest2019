package rc.voxxed.legostore.db;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import rc.voxxed.legostore.model.DeliveryInfo;
import rc.voxxed.legostore.model.LegoSet;
import rc.voxxed.legostore.model.LegoSetDifficulty;
import rc.voxxed.legostore.model.ProductReview;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class DbSeeder implements CommandLineRunner {
    private MongoTemplate mongoTemplate;

    public DbSeeder(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        // Initial delete => start clean
        this.mongoTemplate.dropCollection(LegoSet.class);

        /*
        Lego Sets
         */

        var milleniumFalcon = new LegoSet(
                "Millennium Falcon",
                "Star Wars",
                LegoSetDifficulty.HARD,
                new DeliveryInfo(LocalDate.now().plusDays(3), BigDecimal.valueOf(50), true),
                List.of(
                        new ProductReview("Dan", 7),
                        new ProductReview("Anna", 10),
                        new ProductReview("John", 8)
                )
        );

        var skyPolice = new LegoSet(
                "Sky Police Air Base",
                "City",
                LegoSetDifficulty.MEDIUM,
                new DeliveryInfo(LocalDate.now().plusDays(8), BigDecimal.valueOf(40), true),
                List.of(
                        new ProductReview("Dan", 5),
                        new ProductReview("Andrew", 8)
                )
        );

        var mcLarenSenna = new LegoSet(
                "McLaren Senna",
                "Speed Champions",
                LegoSetDifficulty.EASY,
                new DeliveryInfo(LocalDate.now().plusDays(3), BigDecimal.valueOf(50), false),
                List.of(
                        new ProductReview("Bogdan", 9),
                        new ProductReview("Christa", 9)
                )
        );

        var mindstormsEve = new LegoSet(
                "MINDSTORMS EV3",
                "Mindstorms",
                LegoSetDifficulty.HARD,
                new DeliveryInfo(LocalDate.now().plusDays(21), BigDecimal.valueOf(100), true),
                List.of(
                        new ProductReview("Cosmin", 10),
                        new ProductReview("Jane", 9),
                        new ProductReview("James", 10)
                )
        );

        // Populate collections
        this.mongoTemplate.save(milleniumFalcon);
        this.mongoTemplate.save(skyPolice);
        this.mongoTemplate.save(mindstormsEve);
        this.mongoTemplate.save(mcLarenSenna);

        System.out.printf("Lego Store DataBase Initialized");
    }
}
